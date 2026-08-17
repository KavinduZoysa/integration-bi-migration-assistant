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
 * <p>Because the mediator's Java logic cannot be automatically translated, a stub function is emitted
 * into {@code functions.bal}. The stub takes the {@code Context ctx} followed by each {@code <property>}
 * as a {@code string} argument: a static {@code value} becomes a string literal, and a dynamic
 * {@code expression} is realized the same way {@link PropertyConverter} would and coerced to
 * {@code string}. An unrecognized expression or an inline XML value has no supported translation, so
 * that property is flagged and dropped instead of passed through.
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

        context.ensureContextAvailable();
        Map<String, Expression> resolvedByName = new LinkedHashMap<>();
        for (Property property : classMediator.properties()) {
            resolveArgument(property, context).ifPresent(value -> resolvedByName.put(property.name(), value));
        }

        Function stub = registerStub(context, classMediator, resolvedByName.keySet());
        List<String> parameterNames = stub.parameters().stream().skip(1).map(Parameter::name).toList();

        List<Expression> args = new ArrayList<>();
        args.add(new Expression.VariableReference("ctx"));
        for (String name : parameterNames) {
            args.add(resolvedByName.getOrDefault(name, new StringConstant("")));
        }
        context.statements().add(new Statement.CallStatement(new Expression.FunctionCall(stub.functionName(), args)));
    }

    /** Converts {@code org.example.MyMediator} to {@code myMediator}. */
    private static String stubFunctionName(String className) {
        String simpleName = simpleName(className);
        return Character.toLowerCase(simpleName.charAt(0)) + simpleName.substring(1);
    }

    // Shared with MediateMethodPropertyScanner to match a class name against its Java declaration.
    static String simpleName(String className) {
        return className.contains(".") ? className.substring(className.lastIndexOf('.') + 1) : className;
    }

    // Registers the stub once per class name, persisted so it's reused across artifacts (survives
    // clearArtifactOutput()). A later occurrence reuses the existing shape; properties it has that the
    // shape lacks are reported, not silently dropped. A functionName clash with a different class gets
    // a numeric suffix so each class keeps its own stub.
    private static Function registerStub(ScopeContext context, ClassMediator classMediator,
                                          Set<String> propertyNames) {
        String className = classMediator.className();
        Optional<Function> existing = context.shared().classMediatorStub(className);
        if (existing.isPresent()) {
            reportDroppedProperties(classMediator, propertyNames, existing.get(), context);
            return existing.get();
        }
        String functionName = uniqueStubFunctionName(stubFunctionName(className), context);
        List<String> parameterNames = List.copyOf(propertyNames);
        List<Parameter> params = new ArrayList<>();
        params.add(new Parameter("ctx", new TypeDesc.BallerinaType("Context")));
        parameterNames.forEach(name -> params.add(new Parameter(name, BuiltinType.STRING)));
        Function function = new Function(functionName, params, buildStubBody(context, className));
        context.shared().addClassMediatorStub(className, function);
        return function;
    }

    // baseName, or baseName2/baseName3/... if another class's stub already claimed it.
    private static String uniqueStubFunctionName(String baseName, ScopeContext context) {
        if (!context.shared().isClassMediatorStubNameTaken(baseName)) {
            return baseName;
        }
        for (int suffix = 2; true; suffix++) {
            String candidate = baseName + suffix;
            if (!context.shared().isClassMediatorStubNameTaken(candidate)) {
                return candidate;
            }
        }
    }

    // Reports a property this occurrence has that the already-registered stub has no parameter for,
    // instead of silently passing an empty string in its place.
    private static void reportDroppedProperties(ClassMediator classMediator, Set<String> propertyNames,
                                                  Function existingStub, ScopeContext context) {
        List<String> existingParams = existingStub.parameters().stream().skip(1).map(Parameter::name).toList();
        for (Property property : classMediator.properties()) {
            if (propertyNames.contains(property.name()) && !existingParams.contains(property.name())) {
                PropertyConverter.reportUnsupported(property, context,
                        "This class mediator's stub was already registered from an earlier occurrence "
                                + "without this property; manual conversion required.");
            }
        }
    }

    // Resolves a property to its stub argument, or empty if unsupported.
    private static Optional<Expression> resolveArgument(Property property, ScopeContext context) {
        if (property.hasOmElement()) {
            PropertyConverter.reportUnsupported(property, context,
                    "An inline XML property value has no supported translation for a class mediator "
                            + "argument; manual conversion required.");
            return Optional.empty();
        }
        if (!property.hasExpression() || property.expression().isBlank()) {
            return Optional.of(new StringConstant(property.value()));
        }
        SynapseExpression parsed = SynapseExpressionParser.parse(property.expression(), false);
        if (parsed instanceof SynapseExpression.Literal || parsed instanceof SynapseExpression.UnsupportedCall) {
            PropertyConverter.reportUnsupported(property, context,
                    "The expression is not recognized by the property converter; manual conversion required.");
            return Optional.empty();
        }
        return PropertyConverter.resolveExpression(parsed, property.expression(), SynapseType.STRING, context)
                .map(BallerinaExpression::new);
    }

    /**
     * Builds the stub body. When the original Java can be located, its {@code mediate(MessageContext)}
     * method is scanned for every property it touches (see {@link MediateMethodPropertyScanner}) so
     * each becomes a valid {@code ctx.variables} field, and the source is embedded as a reference
     * comment for the developer to port; otherwise a TODO notes that no source was found.
     */
    private static List<Statement> buildStubBody(ScopeContext context, String className) {
        Optional<JavaSource> resolved = context.shared().javaSourceResolver().resolve(className);
        resolved.ifPresent(source -> MediateMethodPropertyScanner.scan(source, className, context));
        return resolved.map(ClassMediatorConverter::referenceComment)
                .orElseGet(() -> List.of(new Statement.Comment(
                        "TODO: implement '" + className + "' (original Java source not found).")));
    }

    /**
     * Renders the stub body for a located source: a single-line note recording that the
     * original source was found and where it came from.
     */
    private static List<Statement> referenceComment(JavaSource source) {
        return List.of(new Statement.Comment(
                "TODO: implement from the original mediator logic (source located: "
                        + source.origin() + ")."));
    }
}
