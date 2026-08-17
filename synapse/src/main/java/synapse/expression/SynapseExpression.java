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
package synapse.expression;

import java.util.Objects;

/**
 * The parsed form of a Synapse expression. It is one of:
 * <ul>
 *   <li>{@link Literal} — a value written directly in the expression (a number, boolean or quoted
 *       string), e.g. {@code 23} or {@code "hello"};</li>
 *   <li>{@link ScopeExpression} — a whole scope, e.g. {@code $ctx} (the synapse-scope variables) or
 *       {@code $body} (the payload);</li>
 *   <li>{@link PropertyExpression} — a named value within a scope, e.g. {@code $ctx:prop1};</li>
 *   <li>{@link XPathExpression} — an XPath evaluated against a scope or a property within it, e.g.
 *       {@code $ctx:prop1//name} or the payload-rooted {@code //items}.</li>
 * </ul>
 *
 * <p>The XPath of an {@link XPathExpression} is kept verbatim: it is handed to the
 * {@code ballerina/data.xmldata} runtime engine rather than interpreted here.
 */
public sealed interface SynapseExpression {

    // A literal value, e.g. "hello", 23, 2.5, true, a JSON object or an XML (OM) element. The kind
    // determines the Ballerina literal emitted; value is the literal's text without any surrounding
    // quotes. Synapse's LONG and SHORT are parsed as INT and DOUBLE as FLOAT (they share the same
    // Ballerina types); the declared property type still drives the generated field's type.
    record Literal(Kind kind, String value) implements SynapseExpression {

        public enum Kind {
            STRING, INT, FLOAT, BOOLEAN, JSON, OM
        }
    }

    // A whole scope with no property or XPath, e.g. $ctx or $body.
    record ScopeExpression(String scope) implements SynapseExpression {
    }

    // A named value within a scope, e.g. the scope "ctx" / property "prop1" of $ctx:prop1.
    record PropertyExpression(String scope, String propertyName) implements SynapseExpression {
    }

    // An XPath evaluated against a scope or a property within it. An empty propertyName means the
    // XPath applies to the scope root itself, as in the payload-rooted bare XPath //items.
    record XPathExpression(String scope, String propertyName, String xpath) implements SynapseExpression {
    }

    // A get-property(...) call with no Ballerina mapping, e.g. get-property('axis2', 'HTTP_SC') or an
    // unrecognized property name.
    record UnsupportedCall(String raw) implements SynapseExpression {

        public UnsupportedCall {
            Objects.requireNonNull(raw, "raw");
        }
    }
}
