/*
 *  Copyright (c) 2026, WSO2 LLC. (http://www.wso2.com).
 *
 *  WSO2 LLC. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package synapse.converter.bir;

import common.BallerinaModel.Listener.HTTPListener;
import common.BallerinaModel.Parameter;
import common.BallerinaModel.Resource;
import common.BallerinaModel.Service;
import common.BallerinaModel.Statement;
import common.BallerinaModel.TypeDesc;
import synapse.converter.ConversionContext;
import synapse.converter.ConversionContext.UnsupportedEntry;
import synapse.converter.ResourceContext;
import synapse.model.Synapse.InboundEndpoint;
import synapse.model.Synapse.Param;
import synapse.model.Synapse.SequenceMediator;
import synapse.model.Synapse.SynapseNode;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

/**
 * Converts a Synapse {@code <inboundEndpoint>} into a dedicated Ballerina {@code http:Listener} plus a
 * wildcard service that forwards every request straight into the referenced {@code sequence}, with
 * {@code onError} handled the same way a {@code <resource>}'s {@code faultSequence} is.
 *
 * <p>Only the {@code http} protocols are translated; every other built-in protocol and
 * every {@code class}-based (custom Java) inbound endpoint has no generated Ballerina listener
 * equivalent yet and is instead surfaced in the migration report, the same way a {@code <proxy>} is.
 */
public class InboundEndpointConverter implements BIRConverter<ConversionContext> {

    private static final Set<String> HTTP_PROTOCOLS = Set.of("http");
    private static final String HTTP_PORT_PARAM = "inbound.http.port";
    private static final String HTTP_HOST_PARAM = "inbound.http.host";
    private static final String DEFAULT_PORT = "8080";
    private static final String DEFAULT_HOST = "0.0.0.0";
    private static final String LISTENER_SUFFIX = "Listener";
    private static final String ROOT_BASE_PATH = "/";
    // An inbound endpoint has no per-path/per-method dispatch of its own: every request that arrives on
    // its listener is forwarded to the same sequence, so the generated resource matches any method and
    // any path.
    private static final String ANY_PATH = "[string... path]";
    private static final String ANY_METHOD = "'default";
    private static final String CALLER_PARAM = "caller";
    private static final String REQUEST_PARAM = "request";

    @Override
    public void convert(SynapseNode node, ConversionContext context) {
        InboundEndpoint inboundEndpoint = (InboundEndpoint) node;
        if (!HTTP_PROTOCOLS.contains(inboundEndpoint.protocol().toLowerCase(Locale.ROOT))) {
            reportUnsupportedProtocol(inboundEndpoint, context);
            return;
        }

        String listenerName = inboundEndpoint.name() + LISTENER_SUFFIX;
        String port = DEFAULT_PORT;
        String host = DEFAULT_HOST;
        for (Param parameter : inboundEndpoint.parameters()) {
            switch (parameter.name()) {
                case HTTP_PORT_PARAM -> port = parameter.value();
                case HTTP_HOST_PARAM -> host = parameter.value();
                default -> reportUnsupportedParameter(inboundEndpoint, parameter, context);
            }
        }
        context.addListener(new HTTPListener(listenerName, port, host));

        List<Parameter> parameters = List.of(
                new Parameter(CALLER_PARAM, new TypeDesc.BallerinaType("http:Caller")),
                new Parameter(REQUEST_PARAM, new TypeDesc.BallerinaType("http:Request")));

        ResourceContext resourceContext = new ResourceContext(context);
        resourceContext.initContext();
        resourceContext.statements().add(new Statement.BallerinaStatement("check emitPayload(ctx, request);"));
        MediatorConverters.convertMediators(
                List.of(new SequenceMediator(inboundEndpoint.sequenceKey())), resourceContext);
        FaultSequenceConverter.wrap(resourceContext, context, inboundEndpoint.onErrorRef(), "onError",
                "inbound endpoint");

        context.addImports(ConversionContext.MAIN_BAL_FILE, resourceContext.importStatements());
        Resource resource = new Resource(ANY_METHOD, ANY_PATH, parameters,
                Optional.of(new TypeDesc.BallerinaType("error?")), resourceContext.statements());
        context.addService(new Service(ROOT_BASE_PATH, listenerName, List.of(resource)));
    }

    private static void reportUnsupportedProtocol(InboundEndpoint inboundEndpoint, ConversionContext context) {
        String protocolLabel = inboundEndpoint.protocol().isBlank()
                ? "class=\"" + inboundEndpoint.className() + "\""
                : "protocol=\"" + inboundEndpoint.protocol() + "\"";
        String detail = "Inbound endpoint '" + inboundEndpoint.name() + "' uses " + protocolLabel
                + ", which has no generated Ballerina listener equivalent yet; manual conversion required.";
        context.reportUnsupported(new UnsupportedEntry("Unsupported inbound endpoint protocol", "inboundEndpoint",
                context.currentFile(), detail, inboundEndpoint.rawXml()));
    }

    private static void reportUnsupportedParameter(InboundEndpoint inboundEndpoint, Param parameter,
                                                    ConversionContext context) {
        String detail = "Inbound endpoint '" + inboundEndpoint.name() + "' parameter '" + parameter.name()
                + "' is not mapped to any Ballerina construct; manual conversion required.";
        String snippet = "<parameter name=\"" + parameter.name() + "\">" + parameter.value() + "</parameter>";
        context.reportUnsupported(new UnsupportedEntry("Unsupported inbound endpoint parameter", "parameter",
                context.currentFile(), detail, snippet));
    }
}
