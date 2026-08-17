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
package synapse.converter.bir.mediators.classmediator.source;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/** Compiles real, valid {@code .class} bytes at test time so decompiler tests don't need checked-in binaries. */
final class InMemoryJavaCompiler {

    private InMemoryJavaCompiler() {
    }

    static byte[] compile(String className, String source) {
        Path srcDir = TestFixtures.newTempDir("compiler-src-");
        Path outDir = TestFixtures.newTempDir("compiler-out-");
        try {
            Path sourceFile = TestFixtures.writeFile(srcDir, className.replace('.', '/') + ".java", source);

            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            int status = compiler.run(null, null, null, "-d", outDir.toString(), sourceFile.toString());
            if (status != 0) {
                throw new IllegalStateException("Failed to compile fixture class: " + className);
            }

            return Files.readAllBytes(outDir.resolve(className.replace('.', '/') + ".class"));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } finally {
            TestFixtures.deleteRecursively(srcDir);
            TestFixtures.deleteRecursively(outDir);
        }
    }

    static byte[] compileTrivialClass(String className) {
        int lastDot = className.lastIndexOf('.');
        String packageName = lastDot < 0 ? null : className.substring(0, lastDot);
        String simpleName = lastDot < 0 ? className : className.substring(lastDot + 1);
        String source = (packageName == null ? "" : "package " + packageName + ";\n")
                + "public class " + simpleName + " {\n"
                + "    public String greet() {\n"
                + "        return \"hi\";\n"
                + "    }\n"
                + "}\n";
        return compile(className, source);
    }
}
