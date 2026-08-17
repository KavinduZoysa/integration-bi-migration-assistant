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

import common.BallerinaModel.Import;
import common.BallerinaModel.OnFailClause;
import common.BallerinaModel.Parameter;
import common.BallerinaModel.Resource;
import common.BallerinaModel.Service;
import common.BallerinaModel.Statement;
import common.BallerinaModel.TypeBindingPattern;
import common.BallerinaModel.TypeDesc;
import common.BallerinaModel.TypeDesc.BuiltinType;
import synapse.converter.ConversionContext;
import synapse.converter.ConversionContext.UnsupportedEntry;
import synapse.converter.ResourceContext;
import synapse.model.Synapse;
import synapse.model.Synapse.Api;
import synapse.model.Synapse.SequenceMediator;
import synapse.model.Synapse.SynapseNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

/**
 * Converts a Synapse {@code <api>} element into a Ballerina HTTP service.
 */
public class APIConverter implements BIRConverter<ConversionContext> {

    private static final String DEFAULT_LISTENER_REF = "httpListener";
    private static final String ROOT_RESOURCE_PATH = ".";
    // Ballerina rest path parameter for a resource that matches any path, and the "any HTTP method"
    // accessor used when the Synapse resource does not restrict its methods. 'default' is a keyword, so
    // as a resource method name it is escaped with a leading quote.
    private static final String ANY_PATH = "[string... path]";
    private static final String ANY_METHOD = "'default";
    private static final String CALLER_PARAM = "caller";
    private static final String REQUEST_PARAM = "request";

    // Accessors whose HTTP method may carry a request body, so the generated resource takes an
    // http:Request parameter. GET, HEAD and OPTIONS are excluded. 'default (any method) is included and
    // matches the escaped keyword form emitted by ANY_METHOD.
    private static final Set<String> REQUEST_BODY_METHODS = Set.of("post", "put", "patch", "delete", "'default");

    // Type and bound variable name for a faultSequence's 'on fail error err { ... }' clause.
    private static final TypeDesc ERROR_TYPE = new TypeDesc.BallerinaType("error");
    private static final String FAULT_ERROR_VAR = "err";
    private static final TypeBindingPattern ERROR_BINDING = new TypeBindingPattern(ERROR_TYPE, FAULT_ERROR_VAR);

    // Synapse's own hardcoded default (Log + Drop) composes no response at all.
    // Rather than guess at that, we deliberately respond ourselves: log the error
    // and send a real error status with a real error payload.
    private static final Import LOG_IMPORT = new Import("ballerina", "log");
    private static final String UNHANDLED_ERROR_LOG_MESSAGE = "Unhandled error in mediation";
    private static final int UNHANDLED_ERROR_STATUS_CODE = 500;
    private static final String ERROR_MESSAGE_PROPERTY = "ERROR_MESSAGE";

    @Override
    public void convert(SynapseNode node, ConversionContext context) {
        Api api = (Api) node;
        List<Resource> resources = new ArrayList<>();
        for (SynapseNode child : api.resources()) {
            if (child instanceof synapse.model.Synapse.Resource resource) {
                resources.add(convertResource(resource, context));
            }
        }
        Service service = new Service(api.context(), DEFAULT_LISTENER_REF, resources);
        context.addService(service);
    }

    private static Resource convertResource(synapse.model.Synapse.Resource resource, ConversionContext context) {
        // A resource with no 'methods' matches any HTTP method, mapped to the Ballerina 'default' accessor.
        String method = resource.methods().isBlank() ? ANY_METHOD : resource.methods().toLowerCase(Locale.ROOT);

        List<Parameter> parameters = new ArrayList<>();
        for (String queryParam : resource.queryParams()) {
            parameters.add(new Parameter(queryParam, BuiltinType.STRING));
        }
        parameters.add(new Parameter(CALLER_PARAM, new TypeDesc.BallerinaType("http:Caller")));

        ResourceContext resourceContext = new ResourceContext(context);
        resourceContext.initContext();
        if (REQUEST_BODY_METHODS.contains(method)) {
            parameters.add(new Parameter(REQUEST_PARAM, new TypeDesc.BallerinaType("http:Request")));
            resourceContext.statements().add(new Statement.BallerinaStatement("check emitPayload(ctx, request);"));
        }

        if (resource.inSequence() != null) {
            MediatorConverters.convertMediators(resource.inSequence().mediators(), resourceContext);
        }

        // faultSequence="X" takes priority over an inline <faultSequence>. An unresolved X is reported
        // and falls back to the same default as no faultSequence at all. An explicit but empty
        // <faultSequence/> is the author's deliberate choice to leave failures unhandled, so it gets an
        // empty 'on fail'.
        String faultSequenceKey = resource.faultSequenceKey();
        boolean fallsBackToDefault =
                resource.fallsBackToDefaultFaultSequence(key -> context.sequenceMetadata(key).isPresent());
        if (!faultSequenceKey.isBlank()) {
            if (!fallsBackToDefault) {
                wrapInFaultHandler(resourceContext, List.of(new SequenceMediator(faultSequenceKey)));
            } else {
                wrapInGlobalDefaultFaultHandler(resourceContext, context);
                reportUnresolvedFaultSequence(faultSequenceKey, resourceContext);
            }
        } else if (fallsBackToDefault) {
            wrapInGlobalDefaultFaultHandler(resourceContext, context);
        } else if (resource.faultSequence().mediators().isEmpty()) {
            wrapInEmptyFaultHandler(resourceContext);
        } else {
            wrapInFaultHandler(resourceContext, resource.faultSequence().mediators());
        }

        context.addImports(ConversionContext.MAIN_BAL_FILE, resourceContext.importStatements());
        String path = resource.matchAnyPath() ? ANY_PATH : buildResourcePath(resource.path());
        return new Resource(method, path, parameters,
                Optional.of(new TypeDesc.BallerinaType("error?")), resourceContext.statements());
    }

    // Wraps everything converted so far as `do { ... } on fail error err { <faultMediators> }`. The
    // ctx local at index 0 stays outside the 'do': Ballerina doesn't carry do-body locals into 'on
    // fail', and faultSequence mediators (e.g. <respond>) need ctx in scope.
    private static void wrapInFaultHandler(ResourceContext resourceContext, List<SynapseNode> faultMediators) {
        List<Statement> doBody = extractTrailingStatements(resourceContext);

        resourceContext.setResponded(false);
        seedErrorMessageProperty(resourceContext);
        MediatorConverters.convertMediators(faultMediators, resourceContext);
        List<Statement> onFailBody = extractTrailingStatements(resourceContext);

        resourceContext.statements().add(
                new Statement.DoStatement(doBody, new OnFailClause(onFailBody, ERROR_BINDING)));
    }

    // An explicit but empty <faultSequence/>: wrap in a 'do' with an empty 'on fail' so a failure is
    // caught and swallowed (the resource function returns normally, sending no response), rather than
    // running any fault handling at all.
    private static void wrapInEmptyFaultHandler(ResourceContext resourceContext) {
        List<Statement> doBody = extractTrailingStatements(resourceContext);
        resourceContext.statements().add(new Statement.DoStatement(doBody, new OnFailClause(List.of(), ERROR_BINDING)));
    }

    // Extracts everything after the ctx local at index 0 into its own list, clearing it from
    // resourceContext so the next section of the resource body starts clean.
    private static List<Statement> extractTrailingStatements(ResourceContext resourceContext) {
        List<Statement> statements = resourceContext.statements();
        List<Statement> tail = new ArrayList<>(statements.subList(1, statements.size()));
        statements.subList(1, statements.size()).clear();
        return tail;
    }

    // Seeds ERROR_MESSAGE from the caught err so get-property('ERROR_MESSAGE') resolves even when the
    // faultSequence runs as a separately generated function with no lexical access to err.
    private static void seedErrorMessageProperty(ResourceContext resourceContext) {
        resourceContext.shared().addProperty(ERROR_MESSAGE_PROPERTY, "string", "default");
        resourceContext.statements().add(new Statement.BallerinaStatement(
                "ctx.variables." + ERROR_MESSAGE_PROPERTY + " = " + FAULT_ERROR_VAR + ".message();"));
    }

    // Flags a faultSequence="X" attribute that names no known sequence with an inline TODO comment,
    // so the fallback to the default handler is never silent.
    private static void reportUnresolvedFaultSequence(String key, ResourceContext resourceContext) {
        String file = resourceContext.shared().currentFile();
        String origin = file.isEmpty() ? "" : " (from " + file + ")";
        String snippet = "faultSequence=\"" + key + "\"";
        String detail = "Referenced fault sequence '" + key
                + "' was not found among the converted artifacts; falling back to the default error handler.";
        List<Statement> statements = resourceContext.statements();
        statements.add(statements.size() - 1, new Statement.Comment(
                "TODO: Unresolved Synapse fault sequence reference '" + key + "'" + origin + ". " + detail));
        resourceContext.shared().reportUnsupported(
                new UnsupportedEntry("Unresolved fault sequence", "faultSequence", file, detail, snippet));
    }

    // Falls back to the project's "fault" sequence, if defined, else the hardcoded default. Reached for
    // a resource with no faultSequence at all, and for an unresolved faultSequence="X" attribute.
    private static void wrapInGlobalDefaultFaultHandler(ResourceContext resourceContext,
                                                         ConversionContext context) {
        if (context.sequenceMetadata(Synapse.DEFAULT_FAULT_SEQUENCE_KEY).isPresent()) {
            reportImplicitFaultSequence(resourceContext);
            wrapInFaultHandler(resourceContext, List.of(new SequenceMediator(Synapse.DEFAULT_FAULT_SEQUENCE_KEY)));
        } else {
            wrapInDefaultFaultHandler(resourceContext);
        }
    }

    // Surfaces the implicit fallback to the project's "fault" sequence in the migration report
    private static void reportImplicitFaultSequence(ResourceContext resourceContext) {
        String file = resourceContext.shared().currentFile();
        String detail = "This resource has no faultSequence of its own; because a project-level sequence "
                + "named '" + Synapse.DEFAULT_FAULT_SEQUENCE_KEY + "' exists, it is used implicitly as this "
                + "resource's error handler. Verify this matches the intended behavior, or rename the "
                + "sequence if it is unrelated to error handling.";
        resourceContext.shared().reportUnsupported(
                new UnsupportedEntry("Implicit fault sequence", "faultSequence", file, detail, ""));
    }

    // No faultSequence at all, and no project-level "fault" sequence either: log the error and respond
    // with an error status and a JSON error payload carrying the caught error's message.
    private static void wrapInDefaultFaultHandler(ResourceContext resourceContext) {
        List<Statement> doBody = extractTrailingStatements(resourceContext);
        resourceContext.importStatements().add(LOG_IMPORT);
        resourceContext.statements().add(
                new Statement.DoStatement(doBody, new OnFailClause(defaultOnFailBody(), ERROR_BINDING)));
    }

    // Log the error and respond with an error status and a JSON error payload carrying the caught
    // error's message. respond() is a no-op if the caller has already had a response attempted on it,
    // so this is always safe to call.
    private static List<Statement> defaultOnFailBody() {
        return List.of(
                new Statement.BallerinaStatement(
                        "log:printError(\"" + UNHANDLED_ERROR_LOG_MESSAGE + "\", 'error = " + FAULT_ERROR_VAR + ");"),
                new Statement.BallerinaStatement("ctx.payload = {\"error\": " + FAULT_ERROR_VAR + ".message()};"),
                new Statement.BallerinaStatement("ctx.statusCode = " + UNHANDLED_ERROR_STATUS_CODE + ";"),
                new Statement.BallerinaStatement("check respond(ctx);"));
    }

    private static String buildResourcePath(String synapsePath) {
        List<String> segments = new ArrayList<>();
        for (String segment : synapsePath.split("/")) {
            if (segment.isEmpty()) {
                continue;
            }
            if (segment.startsWith("{") && segment.endsWith("}")) {
                String paramName = segment.substring(1, segment.length() - 1);
                segments.add("[string " + paramName + "]");
            } else {
                segments.add(segment);
            }
        }
        return segments.isEmpty() ? ROOT_RESOURCE_PATH : String.join("/", segments);
    }
}
