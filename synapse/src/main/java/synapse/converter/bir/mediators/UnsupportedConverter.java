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
package synapse.converter.bir.mediators;

import common.BallerinaModel.Statement;
import synapse.converter.ConversionContext.UnsupportedEntry;
import synapse.converter.ScopeContext;
import synapse.converter.bir.BIRConverter;
import synapse.converter.bir.MediatorConverters;
import synapse.model.Synapse.SynapseNode;
import synapse.model.Synapse.Unsupported;

/**
 * Converts a mediator that has no Ballerina translation. Rather than failing the migration, it emits a
 * {@code // TO}{@code DO} comment carrying the mediator's original Synapse XML and source file, and
 * records the case for the migration report. For a control-flow wrapper (e.g. {@code <filter>}) whose
 * nested mediator sequences were read into {@link Unsupported#children()}, those children are still
 * converted best-effort after the marker — the wrapper's control flow is not applied, so the marker
 * flags that the result needs manual restructuring.
 */
public class UnsupportedConverter implements BIRConverter<ScopeContext> {

    private static final String CATEGORY = "Unsupported mediator";

    @Override
    public void convert(SynapseNode node, ScopeContext context) {
        if (!(node instanceof Unsupported unsupported)) {
            context.statements().add(new Statement.Comment(
                    "TODO: Unsupported Synapse mediator kind '" + node.kind() + "'. Manual conversion required."));
            return;
        }

        String file = context.shared().currentFile();
        String detail = unsupported.children().isEmpty()
                ? "Mediator not supported; manual conversion required."
                : "Control-flow mediator not supported; the wrapper logic is not applied and nested "
                        + "mediators below need manual restructuring.";
        context.statements().add(new Statement.Comment(todo(unsupported, file, detail)));
        context.shared().reportUnsupported(
                new UnsupportedEntry(CATEGORY, unsupported.tag(), file, detail, unsupported.rawXml()));

        MediatorConverters.convertMediators(unsupported.children(), context);
    }

    private static String todo(Unsupported unsupported, String file, String detail) {
        String origin = file.isEmpty() ? "" : " (from " + file + ")";
        return "TODO: Unsupported Synapse mediator '<" + unsupported.tag() + ">'" + origin + ". " + detail
                + "\nOriginal Synapse:\n" + unsupported.rawXml();
    }
}
