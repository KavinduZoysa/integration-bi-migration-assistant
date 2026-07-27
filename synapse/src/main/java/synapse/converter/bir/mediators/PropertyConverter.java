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

import common.BallerinaModel.Expression.XMLTemplate;
import common.BallerinaModel.Statement;
import common.BallerinaModel.TypeDesc.BuiltinType;
import synapse.converter.ScopeContext;
import synapse.converter.TypeConverter;
import synapse.converter.bir.BIRConverter;
import synapse.expression.SynapseExpressionEmitter;
import synapse.expression.SynapseExpressionEmitter.ExpressionEval;
import synapse.expression.SynapseExpressionParser;
import synapse.model.Synapse.Property;
import synapse.model.Synapse.SynapseNode;
import synapse.model.SynapseType;

import java.util.Optional;

/**
 * Converts a Synapse {@code <property>} mediator. How a property is converted
 * depends on where it
 * lives: a property within a resource contributes to that resource's body,
 * whereas a property
 * outside a resource (e.g. an api-level property) is handled differently. This
 * converter therefore
 * first identifies its scope.
 */
public class PropertyConverter implements BIRConverter<ScopeContext> {

    private static final String TRANSPORT_SCOPE = "transport";
    private static final String AXIS2_SCOPE = "axis2";
    private static final String HTTP_STATUS_PROPERTY = "HTTP_SC";
    private static final String DEFAULT_SCOPE = "default";
    private static final String SYNAPSE_SCOPE = "synapse";
    private static final String REMOVE_ACTION = "remove";

    @Override
    public void convert(SynapseNode node, ScopeContext context) {
        convertProperty((Property) node, context);
    }

    private static void convertProperty(Property property, ScopeContext context) {
        switch (property.scope()) {
            case TRANSPORT_SCOPE -> {
                rejectRemoveAction(property);
                context.ensureContextAvailable();
                if (property.hasExpression()) {
                    // A transport header is a string slot, so the expression is coerced to string.
                    resolveExpression(property.expression(), false, SynapseType.STRING, context).ifPresent(value ->
                            context.statements().add(new Statement.BallerinaStatement(
                                    "ctx.headers[\"" + property.name() + "\"] = " + value + ";")));
                } else {
                    context.statements().add(new Statement.BallerinaStatement(
                            "ctx.headers[\"" + property.name() + "\"] = \"" + property.value() + "\";"));
                }
            }
            case AXIS2_SCOPE -> {
                rejectRemoveAction(property);
                context.ensureContextAvailable();
                if (HTTP_STATUS_PROPERTY.equalsIgnoreCase(property.name())) {
                    // The status code is an int slot, so the value or expression is coerced to int.
                    resolveExpression(rawValue(property), !property.hasExpression(), SynapseType.INTEGER, context)
                            .ifPresent(value -> context.statements().add(new Statement.BallerinaStatement(
                                    "ctx.statusCode = " + value + ";")));
                } else {
                    // A generic axis2 property lands in a map<anydata> slot, which accepts any value, so
                    // no type conversion is applied.
                    ExpressionEval result = emitExpression(rawValue(property), !property.hasExpression(), context);
                    if (result.warning().isEmpty()) {
                        context.statements().add(new Statement.BallerinaStatement(
                                "ctx.axis2[\"" + property.name() + "\"] = " + result.value() + ";"));
                    }
                }
            }
            case DEFAULT_SCOPE, SYNAPSE_SCOPE -> convertDefaultProperty(property, context);
            default -> throw new UnsupportedOperationException("The '" + property.scope()
                    + "' scope is not supported for property '" + property.name() + "'.");
        }
    }

    private static void rejectRemoveAction(Property property) {
        if (REMOVE_ACTION.equals(property.action())) {
            throw new UnsupportedOperationException("The 'remove' action is not supported for property '"
                    + property.name() + "' in the '" + property.scope() + "' scope.");
        }
    }

    private static void convertDefaultProperty(Property property, ScopeContext context) {
        context.ensureContextAvailable();
        if (REMOVE_ACTION.equals(property.action())) {
            context.statements().add(new Statement.BallerinaStatement(
                    "ctx.variables." + property.name() + " = " + BuiltinType.NIL + ";"));
            return;
        }
        context.shared().addProperty(property.name(), toBallerinaType(property.type()), property.scope());
        if (isInlineXml(property)) {
            context.statements().add(new Statement.BallerinaStatement(
                    "ctx.variables." + property.name() + " = " + new XMLTemplate(property.value()) + ";"));
            return;
        }
        resolveExpression(rawValue(property), !property.hasExpression(), property.type(), context).ifPresent(value ->
                context.statements().add(new Statement.BallerinaStatement(
                        "ctx.variables." + property.name() + " = " + value + ";")));
    }

    // The raw text to convert: the expression when present, otherwise the literal value.
    private static String rawValue(Property property) {
        return property.hasExpression() ? property.expression() : property.value();
    }

    // Whether an OM property holds inline XML — a child element the reader serialized into the value.
    // Such content is emitted as an xml template literal, which carries multi-line and quoted content
    // verbatim, rather than through the string-literal path (which would not escape it or type-check).
    private static boolean isInlineXml(Property property) {
        return property.type() == SynapseType.OM && !property.hasExpression()
                && property.value().stripLeading().startsWith("<");
    }

    // Emits the Synapse expression and converts it to expectedType, returning the Ballerina to assign.
    // Returns empty for an unsupported expression: a warning has already been recorded and the emitted
    // placeholder is a string that would not type-check against a non-string target, so the caller omits
    // the assignment and leaves the (optional) target unset.
    private static Optional<String> resolveExpression(String raw, boolean isLiteral, SynapseType expectedType,
                                                      ScopeContext context) {
        ExpressionEval result = emitExpression(raw, isLiteral, context);
        if (result.warning().isPresent()) {
            return Optional.empty();
        }
        String expression = result.value().toString();
        if (result.literalType().isPresent()) {
            return Optional.of(TypeConverter.convertLiteral(expression, result.literalType().get(), expectedType,
                    context.shared()));
        }
        return Optional.of(TypeConverter.convertAnyData(expression, expectedType, context.shared()));
    }

    private static ExpressionEval emitExpression(String raw, boolean isLiteral, ScopeContext context) {
        ExpressionEval result = SynapseExpressionEmitter.emit(SynapseExpressionParser.parse(raw, isLiteral), raw);
        result.warning().ifPresent(warning -> context.statements().add(new Statement.Comment(warning)));
        if (result.requiresXmlData()) {
            context.importStatements().add(SynapseExpressionEmitter.XML_DATA_IMPORT);
        }
        return result;
    }

    private static String toBallerinaType(SynapseType synapseType) {
        return switch (synapseType) {
            case INTEGER, INT, LONG, SHORT -> "int";
            case BOOLEAN -> "boolean";
            case DOUBLE, FLOAT -> "float";
            case OM -> "xml";
            case JSON -> "json";
            default -> "string";
        };
    }
}
