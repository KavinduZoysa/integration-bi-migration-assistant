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

import common.BallerinaModel.Expression;
import common.BallerinaModel.Expression.BallerinaExpression;
import common.BallerinaModel.Expression.Check;
import common.BallerinaModel.Expression.FieldAccess;
import common.BallerinaModel.Expression.StringConstant;
import common.BallerinaModel.Expression.VariableReference;
import common.BallerinaModel.Expression.XMLTemplate;
import common.BallerinaModel.Import;
import org.jetbrains.annotations.NotNull;
import synapse.expression.SynapseExpression.Literal;
import synapse.expression.SynapseExpression.PropertyExpression;
import synapse.expression.SynapseExpression.ScopeExpression;
import synapse.expression.SynapseExpression.XPathExpression;
import synapse.model.SynapseType;

import java.util.Locale;
import java.util.Optional;

/**
 * Renders a parsed {@link SynapseExpression} to the Ballerina
 * {@link Expression} it evaluates to.
 *
 * <p>
 * Roots resolve against the generated {@code Context ctx}: the synapse scope
 * maps to
 * {@code ctx.variables} (a named property to {@code ctx.variables.<name>}),
 * transport to
 * {@code ctx.headers} (a header to {@code ctx.headers["<name>"]}), the
 * {@code HTTP_SC} axis2 property
 * to {@code ctx.statusCode}, Axis2 properties used as XPath roots to
 * {@code ctx.axis2["<name>"]}, and the payload to {@code ctx.payload}. A trailing
 * XPath is not
 * translated; it is evaluated at runtime by
 * {@code ballerina/data.xmldata:transform}, always
 * projecting to {@code string}.
 *
 * <p>
 * Constructs with no clean Ballerina target (an unmapped scope, or an XPath
 * over a non-XML root)
 * do not fail the migration: {@link #emit} returns a {@link ExpressionEval}
 * carrying a {@code warning} and a
 * best-effort placeholder, so the surrounding artifact still converts.
 */
public final class SynapseExpressionEmitter {

    public static final Import XML_DATA_IMPORT = new Import("ballerina", "data.xmldata");

    private static final String CONTEXT_VAR = "ctx";
    private static final String VARIABLES_FIELD = "variables";
    private static final String PAYLOAD_FIELD = "payload";
    private static final String HEADERS_FIELD = "headers";
    private static final String AXIS2_FIELD = "axis2";
    private static final String STATUS_CODE_FIELD = "statusCode";
    private static final String HTTP_STATUS_PROPERTY = "HTTP_SC";
    private static final String STRING_TYPE = "string";

    private SynapseExpressionEmitter() {
    }

    /**
     * The outcome of emitting an expression.
     *
     * @param value           the Ballerina expression to assign
     * @param warning         a comment to surface when the translation is
     *                        best-effort, if any
     * @param requiresXmlData whether the {@code ballerina/data.xmldata} import is
     *                        required
     * @param literalType     the inferred type when the source expression is a
     *                        literal
     */
    public record ExpressionEval(Expression value, Optional<String> warning, boolean requiresXmlData,
                                 Optional<SynapseType> literalType) {

        static ExpressionEval of(Expression value) {
            return new ExpressionEval(value, Optional.empty(), false, Optional.empty());
        }

        static ExpressionEval literal(Expression value, SynapseType type) {
            return new ExpressionEval(value, Optional.empty(), false, Optional.of(type));
        }

        static ExpressionEval transform(Expression value) {
            return new ExpressionEval(value, Optional.empty(), true, Optional.empty());
        }

        static ExpressionEval unsupported(String raw, String reason) {
            return new ExpressionEval(new StringConstant(raw), Optional.of("TODO: " + reason + ": '" + raw + "'"),
                    false, Optional.empty());
        }
    }

    @NotNull
    public static ExpressionEval emit(SynapseExpression expression, String raw) {
        return switch (expression) {
            case Literal literal -> ExpressionEval.literal(emitLiteral(literal), typeOf(literal));
            case ScopeExpression scope -> resolveScopeExpression(scope.scope())
                    .map(val -> ExpressionEval.of(val))
                    .orElseGet(() -> ExpressionEval.unsupported(raw, "unsupported Synapse scope in expression"));
            case PropertyExpression property ->
                resolvePropertyExpression(property.scope(), property.propertyName(), false)
                    .map(val -> ExpressionEval.of(val))
                    .orElseGet(() -> ExpressionEval.unsupported(raw, "unsupported Synapse scope in expression"));
            case XPathExpression xpath -> resolveXPathExpression(xpath, raw);
        };
    }

    private static Expression emitLiteral(Literal literal) {
        return switch (literal.kind()) {
            case STRING -> new StringConstant(literal.value());
            // A JSON literal such as {"name": "Alex"} is already a valid Ballerina mapping
            // constructor.
            case INT, FLOAT, BOOLEAN, JSON -> new BallerinaExpression(literal.value());
            case OM -> new XMLTemplate(literal.value());
        };
    }

    private static SynapseType typeOf(Literal literal) {
        return switch (literal.kind()) {
            case STRING -> SynapseType.STRING;
            case INT -> SynapseType.INTEGER;
            case FLOAT -> SynapseType.FLOAT;
            case BOOLEAN -> SynapseType.BOOLEAN;
            case JSON -> SynapseType.JSON;
            case OM -> SynapseType.OM;
        };
    }

    private static ExpressionEval resolveXPathExpression(XPathExpression xpath, String raw) {
        Expression base;
        if (xpath.propertyName().isEmpty()) {
            base = field(CONTEXT_VAR, PAYLOAD_FIELD);
        } else {
            Optional<Expression> propertyExpr = resolvePropertyExpression(xpath.scope(), xpath.propertyName(), true);
            if (propertyExpr.isEmpty()) {
                String reason = "The '" + xpath.scope() + "' scope is not supported for property '"
                        + xpath.propertyName() + "'.";
                return ExpressionEval.unsupported(raw, reason);
            }
            base = propertyExpr.get();
        }

        return ExpressionEval.transform(genXPathExpr(base, xpath.xpath()));
    }

    private static Optional<Expression> resolveScopeExpression(String scope) {
        return switch (scope.toLowerCase(Locale.ROOT)) {
            case "body" -> Optional.of(field(CONTEXT_VAR, PAYLOAD_FIELD));
            default -> Optional.empty();
        };
    }

    private static Optional<Expression> resolvePropertyExpression(String scope, String name, boolean isXPath) {
        return switch (scope.toLowerCase(Locale.ROOT)) {
            case "ctx" ->
                Optional.of(new FieldAccess(contextField(VARIABLES_FIELD), name));
            case "trp" ->
                Optional.of(new BallerinaExpression(
                        CONTEXT_VAR + "." + HEADERS_FIELD + "[\"" + name + "\"]"));
            case "axis2" -> resolveAxis2Expression(name, isXPath);
            default -> Optional.empty();
        };
    }

    private static Optional<Expression> resolveAxis2Expression(String name, boolean isXPath) {
        if (isXPath) {
            return Optional.of(new BallerinaExpression(
                    CONTEXT_VAR + "." + AXIS2_FIELD + "[\"" + name + "\"]"));
        }
        if (HTTP_STATUS_PROPERTY.equalsIgnoreCase(name)) {
            return Optional.of(contextField(STATUS_CODE_FIELD));
        }
        return Optional.empty();
    }

    // TODO: We may have to convert ballerina expression to xml before passing to xmldata:transform.
    private static Expression genXPathExpr(Expression root, String xpath) {
        String call = "xmldata:transform(<xml>" + root + ", `" + xpath + "`, " + STRING_TYPE + ")";
        return new Check(new BallerinaExpression(call));
    }

    private static FieldAccess contextField(String name) {
        return new FieldAccess(new VariableReference(CONTEXT_VAR), name);
    }

    private static FieldAccess field(String expr, String fieldName) {
        return new FieldAccess(new VariableReference(expr), fieldName);
    }
}
