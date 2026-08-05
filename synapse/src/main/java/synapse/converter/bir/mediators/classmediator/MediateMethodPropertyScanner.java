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

import com.github.javaparser.ParseProblemException;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.BooleanLiteralExpr;
import com.github.javaparser.ast.expr.DoubleLiteralExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.expr.LongLiteralExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import org.jetbrains.annotations.NotNull;
import synapse.converter.ConversionContext.UnsupportedEntry;
import synapse.converter.ScopeContext;
import synapse.converter.bir.mediators.classmediator.source.JavaSource;

import java.util.List;
import java.util.Optional;

/**
 * Scans a class mediator's {@code mediate(MessageContext)} method for every {@code setProperty} touch
 * and registers each into {@code ctx} (see {@code ConversionContext#addProperty}), so
 * {@code ctx.variables.<name>} is a valid field wherever else it's referenced. Never emits statements or
 * otherwise changes the stub body.
 *
 * <p>A {@code getProperty} read is not scanned: it doesn't create a value, so whatever set it — an XML
 * {@code <property scope="default">}, or another mediator's {@code setProperty} already registers the
 * field through its own path.
 *
 * <p>An unparsable source, a missing {@code mediate(MessageContext)} method, or a non-literal property
 * name is reported via {@code ConversionContext#reportUnsupported}. An unresolved type is not reported
 * falling back to {@code anydata} is expected behavior, not a gap.
 */
final class MediateMethodPropertyScanner {

    private static final String MEDIATE_METHOD = "mediate";
    private static final String MESSAGE_CONTEXT_TYPE = "MessageContext";
    private static final String SET_PROPERTY = "setProperty";
    private static final String DEFAULT_SCOPE = "default";
    private static final String ANY_DATA = "anydata";
    private static final String CATEGORY = "Unsupported class mediator property";
    private static final String TAG = "class";

    private MediateMethodPropertyScanner() {
    }

    static void scan(JavaSource source, String className, ScopeContext context) {
        assert source != null : "source must not be null";
        assert className != null : "className must not be null";
        assert context != null : "context must not be null";
        findMediateMethod(source, className, context).ifPresent(found -> scanMethod(found, context));
    }

    // Pairs mediate() with its declaring class, so a setProperty value calling a sibling method can be
    // traced to that method's return type.
    private record MediateMethod(ClassOrInterfaceDeclaration declaringClass, MethodDeclaration method) {
    }

    @NotNull
    private static Optional<MediateMethod> findMediateMethod(JavaSource source, String className,
                                                               ScopeContext context) {
        CompilationUnit unit;
        try {
            unit = StaticJavaParser.parse(source.source());
        } catch (ParseProblemException e) {
            reportUnsupported(context, "The mediator's Java source could not be parsed; no property "
                    + "touches could be scanned.", classSnippet(className));
            return Optional.empty();
        }
        String simpleName = ClassMediatorConverter.simpleName(className);
        Optional<MediateMethod> found = unit.findAll(ClassOrInterfaceDeclaration.class).stream()
                .filter(type -> type.getNameAsString().equals(simpleName))
                .flatMap(type -> type.getMethodsByName(MEDIATE_METHOD).stream()
                        .filter(MediateMethodPropertyScanner::isMediateMethod)
                        .map(method -> new MediateMethod(type, method)))
                .findFirst();
        if (found.isEmpty()) {
            reportUnsupported(context, "No mediate(MessageContext) method was found; no property touches "
                    + "could be scanned.", classSnippet(className));
        }
        return found;
    }

    private static String classSnippet(String className) {
        return "<class name=\"" + className + "\">";
    }

    private static void reportUnsupported(ScopeContext context, String detail, String snippet) {
        context.shared().reportUnsupported(
                new UnsupportedEntry(CATEGORY, TAG, context.shared().currentFile(), detail, snippet));
    }

    private static boolean isMediateMethod(MethodDeclaration method) {
        return method.getBody().isPresent() && method.getParameters().size() == 1
                && method.getParameter(0).getType().asString().endsWith(MESSAGE_CONTEXT_TYPE);
    }

    // Walks every call, not just top-level statements, so a touch nested in an if/loop is still found.
    private static void scanMethod(MediateMethod found, ScopeContext context) {
        MethodDeclaration method = found.method();
        String paramName = method.getParameter(0).getNameAsString();
        List<VariableDeclarator> locals = method.findAll(VariableDeclarator.class);
        List<MethodDeclaration> siblingMethods = found.declaringClass().getMethods();
        for (MethodCallExpr call : method.findAll(MethodCallExpr.class)) {
            if (isCallOn(call, paramName) && SET_PROPERTY.equals(call.getNameAsString())
                    && call.getArguments().size() == 2) {
                registerWrite(call, locals, siblingMethods, context);
            }
        }
    }

    private static boolean isCallOn(MethodCallExpr call, String paramName) {
        return call.getScope().isPresent() && call.getScope().get() instanceof NameExpr scope
                && scope.getNameAsString().equals(paramName);
    }

    // mc.setProperty("x", valueExpr): traces a literal, a local variable, or a sibling method call to a
    // type; anything else falls back to anydata.
    private static void registerWrite(MethodCallExpr call, List<VariableDeclarator> locals,
                                       List<MethodDeclaration> siblingMethods, ScopeContext context) {
        Optional<String> name = propertyName(call);
        if (name.isEmpty()) {
            reportUnresolvedPropertyName(call, context);
            return;
        }
        Expression value = call.getArgument(1);
        String type = literalType(value).or(() -> tracedLocalType(value, locals))
                .or(() -> tracedMethodReturnType(value, siblingMethods))
                .map(MediateMethodPropertyScanner::ballerinaType).orElse(ANY_DATA);
        context.shared().addProperty(name.get(), type, DEFAULT_SCOPE);
    }

    @NotNull
    private static Optional<String> propertyName(MethodCallExpr call) {
        if (call.getArgument(0) instanceof StringLiteralExpr literal) {
            return Optional.of(literal.asString());
        }
        return Optional.empty();
    }

    // The name isn't a string literal (a constant, a computed name, ...), so nothing can be registered;
    // report it instead of silently dropping the touch.
    private static void reportUnresolvedPropertyName(MethodCallExpr call, ScopeContext context) {
        reportUnsupported(context, "The property name is not a string literal; manual conversion "
                + "required.", call.toString());
    }

    @NotNull
    private static Optional<String> literalType(Expression value) {
        if (value instanceof StringLiteralExpr) {
            return Optional.of("String");
        }
        if (value instanceof BooleanLiteralExpr) {
            return Optional.of("boolean");
        }
        if (value instanceof DoubleLiteralExpr) {
            return Optional.of("double");
        }
        if (value instanceof IntegerLiteralExpr || value instanceof LongLiteralExpr) {
            return Optional.of("int");
        }
        return Optional.empty();
    }

    // Ambiguous when more than one local (anywhere in the method, including other scopes) shares the
    // name. Falls back to anydata rather than guessing which declaration actually applies.
    @NotNull
    private static Optional<String> tracedLocalType(Expression value, List<VariableDeclarator> locals) {
        if (!(value instanceof NameExpr nameExpr)) {
            return Optional.empty();
        }
        List<VariableDeclarator> matches = locals.stream()
                .filter(decl -> decl.getNameAsString().equals(nameExpr.getNameAsString()))
                .toList();
        return matches.size() == 1 ? Optional.of(matches.get(0).getType().asString()) : Optional.empty();
    }

    // A bare call to a same-class method, matched by name and arity; not full overload resolution.
    // A scoped call, no match, or more than one same-arity overload (ambiguous return type) leaves the
    // caller to fall back to anydata.
    @NotNull
    private static Optional<String> tracedMethodReturnType(Expression value, List<MethodDeclaration> siblingMethods) {
        if (!(value instanceof MethodCallExpr call) || call.getScope().isPresent()) {
            return Optional.empty();
        }
        List<MethodDeclaration> matches = siblingMethods.stream()
                .filter(candidate -> candidate.getNameAsString().equals(call.getNameAsString())
                        && candidate.getParameters().size() == call.getArguments().size())
                .toList();
        return matches.size() == 1 ? Optional.of(matches.get(0).getType().asString()) : Optional.empty();
    }

    private static String ballerinaType(String javaType) {
        return switch (javaType) {
            case "String" -> "string";
            case "boolean", "Boolean" -> "boolean";
            case "int", "Integer", "long", "Long" -> "int";
            case "double", "Double", "float", "Float" -> "float";
            default -> ANY_DATA;
        };
    }
}
