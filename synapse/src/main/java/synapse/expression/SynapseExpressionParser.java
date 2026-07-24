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

import org.jetbrains.annotations.NotNull;
import synapse.expression.SynapseExpression.Literal;
import synapse.expression.SynapseExpression.PropertyExpression;
import synapse.expression.SynapseExpression.ScopeExpression;
import synapse.expression.SynapseExpression.XPathExpression;

import java.util.regex.Pattern;

/**
 * Parses a raw Synapse expression into a {@link SynapseExpression}. Handles both literals (numbers,
 * booleans, quoted strings) and scope/property/XPath references.
 *
 * <p>The forms overlap — an unquoted {@code before} is a valid relative XPath while {@code "before"}
 * is a string literal — so a fixed precedence disambiguates them: quoted string, then boolean, then
 * int, then float, then a {@code $}-prefixed scope reference, and finally a bare XPath rooted at the
 * payload. The XPath tail is never interpreted here; it is kept verbatim for the
 * {@code ballerina/data.xmldata} runtime engine.
 */
public final class SynapseExpressionParser {

    private static final String BODY_SCOPE = "body";
    private static final String DEFAULT_SCOPE = "ctx";
    private static final char SCOPE_SEPARATOR = ':';
    private static final char PATH_SEPARATOR = '/';

    private static final Pattern INT_PATTERN = Pattern.compile("[+-]?\\d+");
    private static final Pattern FLOAT_PATTERN = Pattern.compile("[+-]?(\\d+\\.\\d*|\\.\\d+|\\d+)([eE][+-]?\\d+)?");

    private SynapseExpressionParser() {
    }

    @NotNull
    public static SynapseExpression parse(String raw, boolean isLiteral) {
        String expression = raw.trim();
        if (isLiteral) {
            return parseLiteral(expression);
        }

        return parseExpression(expression);
    }

    private static Literal parseLiteral(String literal) {
        if ("true".equals(literal) || "false".equals(literal)) {
            return new Literal(Literal.Kind.BOOLEAN, literal);
        }
        if (INT_PATTERN.matcher(literal).matches()) {
            return new Literal(Literal.Kind.INT, literal);
        }
        if (FLOAT_PATTERN.matcher(literal).matches()) {
            return new Literal(Literal.Kind.FLOAT, literal);
        }
        String stringLiteral = literal;
        if (isQuoted(stringLiteral)) {
            stringLiteral = stringLiteral.substring(1, stringLiteral.length() - 1);
        }
        if (isJsonLiteral(stringLiteral)) {
            return new Literal(Literal.Kind.JSON, stringLiteral);
        }
        return new Literal(Literal.Kind.STRING, stringLiteral);
    }

    private static boolean isQuoted(String expression) {
        if (expression.length() < 2) {
            return false;
        }
        char first = expression.charAt(0);
        char last = expression.charAt(expression.length() - 1);
        return (first == '"' || first == '\'') && first == last;
    }

    private static boolean isJsonLiteral(String literal) {
        return (literal.startsWith("{") && literal.endsWith("}")) || 
            (literal.startsWith("[") && literal.endsWith("]"));
    }

    @NotNull
    private static SynapseExpression parseExpression(String expression) {
        if (expression.isEmpty()) {
            // TODO: This should be handled to replicate the same behavior as Synapse.
            throw new IllegalArgumentException("Expression must not be empty");
        }

        if (expression.startsWith("//")) {
            return new XPathExpression(BODY_SCOPE, "", expression);
        }

        if (expression.charAt(0) == '$') {
            String withoutDollar = expression.substring(1);
            int pathStart = withoutDollar.indexOf(PATH_SEPARATOR);
            String head = pathStart >= 0 ? withoutDollar.substring(0, pathStart) : withoutDollar;
            String xpath = pathStart >= 0 ? withoutDollar.substring(pathStart) : "";
    
            int separator = head.indexOf(SCOPE_SEPARATOR);
            String scope = separator >= 0 ? head.substring(0, separator) : head;
            String propertyName = separator >= 0 ? head.substring(separator + 1) : "";
    
            if (!xpath.isEmpty()) {
                return new XPathExpression(scope, propertyName, xpath);
            }
            if (!propertyName.isEmpty()) {
                return new PropertyExpression(scope, propertyName);
            }
            if (scope.equals(BODY_SCOPE)) {
                return new ScopeExpression(BODY_SCOPE);
            }
            return new Literal(Literal.Kind.STRING, expression);
        }

        if (expression.startsWith("json-eval")) {
            // TODO: Handle JSON path
        }

        return parseLiteral(expression);
    }
}
