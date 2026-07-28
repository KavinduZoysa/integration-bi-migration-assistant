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
package synapse.converter;

import common.BallerinaModel.Function;
import common.BallerinaModel.Parameter;
import common.BallerinaModel.Statement;
import common.BallerinaModel.Statement.BallerinaStatement;
import common.BallerinaModel.TypeDesc.BallerinaType;
import synapse.expression.SynapseExpressionEmitter;
import synapse.model.SynapseType;

import java.util.Arrays;
import java.util.List;

/**
 * Bridges the gap between the type a Synapse expression evaluates to and the type a property expects,
 * emitting the Ballerina needed to convert one to the other.
 *
 * <p>Two families of conversion, matching whether the source type is known at conversion time:
 * <ul>
 *   <li><b>Literal source</b> ({@link #convertLiteral}) — the value is a literal whose type is known,
 *       so a strongly typed converter is used, e.g. {@code stringToInt(string) returns int|error}.
 *       No converter is emitted when the value already assigns directly (same Ballerina type, an int
 *       literal into a float, or anything into {@code json}); a {@code .toString()} is appended inline
 *       when the target is a string.</li>
 *   <li><b>Non-literal source</b> ({@link #convertAnyData}) — the value's runtime type is unknown, so
 *       an {@code anydata}-accepting converter is used, e.g. {@code convertToInt(anydata) returns
 *       int|error}, which dispatches on the runtime type and errors on an unconvertible value.</li>
 * </ul>
 *
 * <p>Numeric narrowing truncates ({@code <int>}); string parsing ({@code int:fromString} etc.) and any
 * unconvertible pairing surface a runtime error propagated through {@code check}. Every generated
 * converter is registered on the {@link ConversionContext} so a converter needed by many properties is
 * emitted into {@code functions.bal} once.
 */
public final class TypeConverter {

    private static final String VALUE_PARAM = "v";

    private TypeConverter() {
    }

    private enum BalType {
        STRING("string"), INT("int"), FLOAT("float"), BOOLEAN("boolean"), XML("xml"), JSON("json");

        private final String token;

        BalType(String token) {
            this.token = token;
        }

        private String capitalized() {
            return Character.toUpperCase(token.charAt(0)) + token.substring(1);
        }
    }

    private static BalType balTypeOf(SynapseType type) {
        return switch (type) {
            case STRING -> BalType.STRING;
            case INTEGER, INT, LONG, SHORT -> BalType.INT;
            case DOUBLE, FLOAT -> BalType.FLOAT;
            case BOOLEAN -> BalType.BOOLEAN;
            case OM -> BalType.XML;
            case JSON -> BalType.JSON;
        };
    }

    /**
     * Converts a literal of a known type to the expected type, registering the typed converter on the
     * context when one is needed. Returns the Ballerina expression to assign.
     */
    public static String convertLiteral(String expr, SynapseType actualType, SynapseType expectedType,
                                        ConversionContext context) {
        BalType src = balTypeOf(actualType);
        BalType tgt = balTypeOf(expectedType);
        if (assignsDirectly(src, tgt)) {
            return expr;
        }
        if (tgt == BalType.STRING) {
            return expr + ".toString()";
        }
        Converter converter = typedConverter(src.token + "To" + tgt.capitalized(), src, tgt);
        context.addConverterFunction(converter.function());
        return call(converter, expr);
    }

    /**
     * Converts a value whose runtime type is unknown to the expected type, registering the
     * {@code anydata} converter on the context. Returns the Ballerina expression to assign.
     */
    public static String convertAnyData(String expr, SynapseType expectedType, ConversionContext context) {
        BalType tgt = balTypeOf(expectedType);
        Converter converter = anyDataConverter("convertTo" + tgt.capitalized(), tgt);
        context.addConverterFunction(converter.function());
        return call(converter, expr);
    }

    /**
     * Registers the {@code convertToXml(anydata)} helper the emitter uses to coerce an XPath root to
     * {@code xml}. The call site itself is built by the emitter; this only ensures the function exists.
     */
    public static void requireConvertToXml(ConversionContext context) {
        context.addConverterFunction(
                anyDataConverter(SynapseExpressionEmitter.CONVERT_TO_XML_FUNCTION, BalType.XML).function());
    }

    private static boolean assignsDirectly(BalType src, BalType tgt) {
        return src == tgt || tgt == BalType.JSON || (src == BalType.INT && tgt == BalType.FLOAT);
    }

    private static String call(Converter converter, String expr) {
        String invocation = converter.name() + "(" + expr + ")";
        return converter.canError() ? "check " + invocation : invocation;
    }

    private static Converter typedConverter(String name, BalType src, BalType tgt) {
        if (tgt == BalType.INT && src == BalType.FLOAT) {
            return new Converter(name, false, function(name, src.token, "int", "return <int>v;"));
        }
        String returnType = tgt.token + "|error";
        if (src == BalType.STRING) {
            return new Converter(name, true, function(name, src.token, returnType, "return " + parse(tgt) + ";"));
        }
        return new Converter(name, true, function(name, src.token, returnType,
                "return error(\"Cannot convert " + src.token + " to " + tgt.token + ".\");"));
    }

    private static String parse(BalType tgt) {
        return switch (tgt) {
            case INT -> "int:fromString(v)";
            case FLOAT -> "float:fromString(v)";
            case BOOLEAN -> "boolean:fromString(v)";
            case XML -> "xml:fromString(v)";
            // STRING and JSON targets never reach here: they are handled inline / as direct assignments.
            default -> throw new IllegalStateException("Unexpected literal converter target: " + tgt);
        };
    }

    private static Converter anyDataConverter(String name, BalType tgt) {
        return switch (tgt) {
            case STRING -> new Converter(name, false, function(name, "anydata", "string", "return v.toString();"));
            case INT -> new Converter(name, true, function(name, "anydata", "int|error",
                    "if v is int { return v; }",
                    "if v is float { return <int>v; }",
                    "if v is decimal { return <int>v; }",
                    "if v is string { return int:fromString(v); }",
                    "return error(\"Cannot convert the given value to int.\");"));
            case FLOAT -> new Converter(name, true, function(name, "anydata", "float|error",
                    "if v is float { return v; }",
                    "if v is int { return <float>v; }",
                    "if v is decimal { return <float>v; }",
                    "if v is string { return float:fromString(v); }",
                    "return error(\"Cannot convert the given value to float.\");"));
            case BOOLEAN -> new Converter(name, true, function(name, "anydata", "boolean|error",
                    "if v is boolean { return v; }",
                    "if v is string { return boolean:fromString(v); }",
                    "return error(\"Cannot convert the given value to boolean.\");"));
            case XML -> new Converter(name, true, function(name, "anydata", "xml|error",
                    "if v is xml { return v; }",
                    "if v is string { return xml:fromString(v); }",
                    "return error(\"Cannot convert the given value to xml.\");"));
            case JSON -> new Converter(name, true, function(name, "anydata", "json|error",
                    "if v is json|int|float|decimal|string|boolean { return v; }",
                    "return error(\"Cannot convert the given value to json.\");"));
        };
    }

    private static Function function(String name, String paramType, String returnType, String... bodyLines) {
        List<Statement> body = Arrays.stream(bodyLines)
                .map(line -> (Statement) new BallerinaStatement(line)).toList();
        return new Function(name, List.of(new Parameter(VALUE_PARAM, new BallerinaType(paramType))),
                new BallerinaType(returnType), body);
    }

    private record Converter(String name, boolean canError, Function function) {
    }
}
