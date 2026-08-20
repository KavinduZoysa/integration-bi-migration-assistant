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

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import synapse.expression.SynapseExpression.Literal;
import synapse.expression.SynapseExpressionEmitter.ExpressionEval;
import synapse.model.SynapseType;

import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Verifies that Synapse expressions parse and emit to the expected Ballerina:
 * literals emit directly,
 * scope/property references resolve against {@code ctx}, XPath is delegated to
 * {@code xmldata:transform}, and unmapped constructs degrade to a best-effort
 * placeholder + warning.
 */
public class SynapseExpressionTest {

    @Test(dataProvider = "supportedCases")
    public void emitsExpectedBallerina(String expression, String expectedValue, boolean expectsXmlData) {
        ExpressionEval result = emit(expression);
        assertEquals(result.value().toString(), expectedValue);
        assertFalse(result.warning().isPresent(), "unexpected warning for '" + expression + "'");
        assertEquals(result.requiresXmlData(), expectsXmlData);
    }

    @DataProvider
    public Object[][] supportedCases() {
        return new Object[][] {
                // Literals. Synapse's LONG/SHORT map to int and DOUBLE to float; the value is
                // inferred
                // from content, so these emit the same as INTEGER/FLOAT (see
                // parsesLiteralKindFromContent).
                // { "\"hello\"", "\"hello\"", false },
                { "'hello'", "\"hello\"", false },
                { "hello", "\"hello\"", false },
                { "23", "23", false },
                { "-4", "-4", false },
                { "9999999999", "9999999999", false },
                { "2.5", "2.5", false },
                { "true", "true", false },
                { "false", "false", false },
                { "$foo", "\"$foo\"", false },
                // JSON literal (valid Ballerina mapping) and OM (XML element) literal.
                { "{\"name\": \"Alex\"}", "{\"name\": \"Alex\"}", false },
                { "[1, 2, 3]", "[1, 2, 3]", false },
                { "<foo>bar</foo>", "\"<foo>bar</foo>\"", false },
                { "<xml-element/>", "\"<xml-element/>\"", false },
                // ScopeExpression: a whole scope.
                { "$ctx", "\"$ctx\"", false },
                { "$body", "ctx.payload", false },
                { "$trp", "\"$trp\"", false },
                // PropertyExpression: a named value within a scope.
                { "$ctx:prop1", "ctx.variables.prop1", false },
                { "$trp:Host", "ctx.headers[\"Host\"]", false },
                { "$axis2:HTTP_SC", "ctx.statusCode", false },
                // XPathExpression: an XPath over a property.
                { "$ctx:prop1//name",
                        "check xmldata:transform(check convertToXml(ctx.variables.prop1), `//name`, string)", true },
                // XPathExpression: a bare XPath, implicitly rooted at the payload.
                { "//items", "check xmldata:transform(check convertToXml(ctx.payload), `//items`, string)", true },
                { "$body//items", "check xmldata:transform(check convertToXml(ctx.payload), `//items`, string)", true },
                { "$trp:Host//x",
                        "check xmldata:transform(check convertToXml(ctx.headers[\"Host\"]), `//x`, string)", true },
                { "$axis2:HTTP_SC//x",
                        "check xmldata:transform(check convertToXml(ctx.axis2[\"HTTP_SC\"]), `//x`, string)", true },
        };
    }

    /**
     * The literal kind is inferred from content alone; the property's declared
     * {@code type} is not
     * consulted here (reconciling a declared type with the content — e.g.
     * {@code type="STRING"} over a
     * numeric value — is a converter-level conversion handled separately).
     * Consequently Synapse's LONG
     * and SHORT parse as {@link Literal.Kind#INT} and DOUBLE as
     * {@link Literal.Kind#FLOAT}.
     */
    @Test(dataProvider = "literalKindCases")
    public void parsesLiteralKindFromContent(String expression, Literal.Kind expectedKind) {
        SynapseExpression parsed = SynapseExpressionParser.parse(expression, true, Set.of());
        assertTrue(parsed instanceof Literal, "'" + expression + "' should parse as a literal");
        assertEquals(((Literal) parsed).kind(), expectedKind);
    }

    @Test(dataProvider = "literalKindCases")
    public void emittedLiteralCarriesType(String expression, Literal.Kind expectedKind) {
        SynapseExpression parsed = SynapseExpressionParser.parse(expression, true, Set.of());
        ExpressionEval result = SynapseExpressionEmitter.emit(parsed, expression);
        assertEquals(result.literalType(), java.util.Optional.of(toSynapseType(expectedKind)));
    }

    private static SynapseType toSynapseType(Literal.Kind kind) {
        return switch (kind) {
            case STRING -> SynapseType.STRING;
            case INT -> SynapseType.INTEGER;
            case FLOAT -> SynapseType.FLOAT;
            case BOOLEAN -> SynapseType.BOOLEAN;
            case JSON -> SynapseType.JSON;
            case OM -> SynapseType.OM;
        };
    }

    @DataProvider
    public Object[][] literalKindCases() {
        return new Object[][] {
                { "\"hi\"", Literal.Kind.STRING },
                { "42", Literal.Kind.INT }, // INTEGER, LONG and SHORT all infer as INT
                { "9999999999", Literal.Kind.INT }, // a LONG-range value is still INT (Ballerina int is 64-bit)
                { "2.5", Literal.Kind.FLOAT }, // FLOAT and DOUBLE both infer as FLOAT
                { "true", Literal.Kind.BOOLEAN },
                { "{\"a\": 1}", Literal.Kind.JSON },
                { "<person><name>Alex</name></person>", Literal.Kind.STRING },
                { "", Literal.Kind.STRING },
        };
    }

    @Test(dataProvider = "unsupportedCases")
    public void unsupportedDegradesToPlaceholder(String expression) {
        ExpressionEval result = emit(expression);
        assertTrue(result.warning().isPresent(), "expected a warning for '" + expression + "'");
        assertEquals(result.value().toString(), "\"" + expression + "\"");
        assertFalse(result.requiresXmlData());
    }

    @DataProvider
    public Object[][] unsupportedCases() {
        return new Object[][] {
                { "$query:q" }, // scope with no ctx target
                { "get-property('axis2', 'HTTP_SC')" }, // two-argument explicit-scope form is unsupported
        };
    }

    /**
     * A {@code get-property(...)} call only resolves to {@code $ctx:name} when {@code name} is known to
     * have been set as a default-scope property somewhere in the project (see
     * {@link SynapseExpressionParser#parse}); otherwise it degrades to a placeholder like any other
     * unsupported construct, exercised via {@link #unsupportedCases}.
     */
    @Test
    public void getPropertyResolvesWhenAvailable() {
        String expression = "get-property('ERROR_MESSAGE')";
        SynapseExpression parsed = SynapseExpressionParser.parse(expression, false, Set.of("ERROR_MESSAGE"));
        ExpressionEval result = SynapseExpressionEmitter.emit(parsed, expression);
        assertEquals(result.value().toString(), "ctx.variables.ERROR_MESSAGE");
        assertFalse(result.warning().isPresent());
    }

    private static ExpressionEval emit(String expression) {
        return SynapseExpressionEmitter.emit(SynapseExpressionParser.parse(expression, false, Set.of()), expression);
    }
}
