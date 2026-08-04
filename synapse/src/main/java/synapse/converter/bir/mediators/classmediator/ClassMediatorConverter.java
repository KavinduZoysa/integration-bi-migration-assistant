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
package synapse.converter.bir.mediators.classmediator;

import common.BallerinaModel.Expression;
import common.BallerinaModel.Expression.BallerinaExpression;
import common.BallerinaModel.Expression.StringConstant;
import common.BallerinaModel.Function;
import common.BallerinaModel.Parameter;
import common.BallerinaModel.Statement;
import common.BallerinaModel.TypeDesc;
import common.BallerinaModel.TypeDesc.BuiltinType;
import synapse.converter.ScopeContext;
import synapse.converter.bir.BIRConverter;
import synapse.converter.bir.mediators.PropertyConverter;
import synapse.converter.bir.mediators.classmediator.source.JavaSource;
import synapse.expression.SynapseExpression;
import synapse.expression.SynapseExpressionParser;
import synapse.model.Synapse.ClassMediator;
import synapse.model.Synapse.Property;
import synapse.model.Synapse.SynapseNode;
import synapse.model.SynapseType;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Converts a Synapse {@code <class>} mediator into a call to a generated Ballerina stub function.
 *
 * <p>Because the mediator's Java logic cannot be automatically translated, a stub function is
 * emitted into {@code functions.bal}. The stub takes the {@code Context ctx} followed by each
 * {@code <property>} as a {@code string} argument: a static {@code value} becomes a string literal, and
 * a dynamic {@code expression} is realized the same way {@link PropertyConverter} would and coerced to
 * {@code string}. An expression the property converter does not recognize (e.g. the old-style
 * {@code get-property(...)} function-call syntax) has no supported translation, so that property is
 * dropped and flagged with a TODO instead of being passed through as the literal expression text.
 *
 * <p>When the mediator's original Java source can be located (see {@code JavaSourceResolver}) it is
 * embedded in the stub body as a reference comment, tagged with its {@link JavaSource.Origin}, so the
 * developer can port it in place. When it cannot be found, a TODO records that the source was
 * unavailable. Either way the developer replaces the body with equivalent Ballerina.
 */
public class ClassMediatorConverter implements BIRConverter<ScopeContext> {

    @Override
    public void convert(SynapseNode node, ScopeContext context) {
        ClassMediator classMediator = (ClassMediator) node;
        String functionName = stubFunctionName(classMediator.className());

        context.ensureContextAvailable();
        Map<String, Expression> resolvedByName = new LinkedHashMap<>();
        for (Property property : classMediator.properties()) {
            resolveArgument(property, context).ifPresent(value -> resolvedByName.put(property.name(), value));
        }

        List<String> parameterNames = registerStub(context, functionName, classMediator, resolvedByName.keySet());

        List<Expression> args = new ArrayList<>();
        args.add(new Expression.VariableReference("ctx"));
        for (String name : parameterNames) {
            args.add(resolvedByName.getOrDefault(name, new StringConstant("")));
        }
        context.statements().add(new Statement.CallStatement(new Expression.FunctionCall(functionName, args)));
    }

    /** Converts {@code org.example.MyMediator} to {@code myMediator}. */
    private static String stubFunctionName(String className) {
        String simpleName = className.contains(".")
                ? className.substring(className.lastIndexOf('.') + 1)
                : className;
        return Character.toLowerCase(simpleName.charAt(0)) + simpleName.substring(1);
    }

    // Registers the stub the first time its class name is converted, fixing its parameter shape from
    // that occurrence alone. A later occurrence of the same class name reuses that exact shape rather
    // than building its own: the class name always maps to the one already registered function, so
    // every call site must agree on its parameter count regardless of what that occurrence's own
    // properties resolved to. Returns the parameter names (excluding ctx) in the order
    // the caller must supply them.
    private static List<String> registerStub(ScopeContext context, String functionName, ClassMediator classMediator,
                                              Set<String> propertyNames) {
        Optional<Function> existing = context.shared().functions().stream()
                .filter(f -> f.functionName().equals(functionName))
                .findFirst();
        if (existing.isPresent()) {
            return existing.get().parameters().stream().skip(1).map(Parameter::name).toList();
        }
        List<String> parameterNames = List.copyOf(propertyNames);
        List<Parameter> params = new ArrayList<>();
        params.add(new Parameter("ctx", new TypeDesc.BallerinaType("Context")));
        parameterNames.forEach(name -> params.add(new Parameter(name, BuiltinType.STRING)));
        context.shared().addFunction(new Function(
                functionName, params, buildStubBody(context, classMediator.className())));
        return parameterNames;
    }

    // Resolves a property to the string argument passed to the stub, or empty when it has no
    // supported translation, in which case it is omitted from both the stub's parameters and the call
    // site's arguments.
    private static Optional<Expression> resolveArgument(Property property, ScopeContext context) {
        if (!property.hasExpression() || property.expression().isBlank()) {
            return Optional.of(new StringConstant(property.value()));
        }
        SynapseExpression parsed = SynapseExpressionParser.parse(property.expression(), false);
        if (parsed instanceof SynapseExpression.Literal) {
            PropertyConverter.reportUnsupported(property, context,
                    "The expression is not recognized by the property converter; manual conversion required.");
            return Optional.empty();
        }
        return PropertyConverter.resolveExpression(parsed, property.expression(), SynapseType.STRING, context)
                .map(BallerinaExpression::new);
    }

    /**
     * Builds the stub body. When the original Java can be located it is embedded as a reference
     * comment for the developer to port; otherwise a TODO notes that no source was found.
     */
    private static List<Statement> buildStubBody(ScopeContext context, String className) {
        return context.shared().javaSourceResolver().resolve(className)
                .map(ClassMediatorConverter::referenceComment)
                .orElseGet(() -> List.of(new Statement.BallerinaStatement(
                        "// TODO: implement '" + className + "' (original Java source not found).")));
    }

    /**
     * Renders the stub body for a located source: a single-line note recording that the
     * original source was found and where it came from.
     */
    public static List<Statement> referenceComment(JavaSource source) {
        return List.of(new Statement.BallerinaStatement(
                "// TODO: implement from the original mediator logic (source located: "
                        + source.origin() + ")."));
    }
}
