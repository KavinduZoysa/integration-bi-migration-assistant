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

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CfrDecompilerTest {

    @Test
    public void testDecompilesValidClass() {
        byte[] classBytes = InMemoryJavaCompiler.compileTrivialClass("test.Greeter");

        Optional<String> result = new CfrDecompiler().decompile("test.Greeter", classBytes);

        Assert.assertTrue(result.isPresent());
        Assert.assertTrue(result.get().contains("class Greeter"));
        Assert.assertTrue(result.get().contains("greet"));
    }

    @Test
    public void testRejectsNullClassName() {
        Set<String> before = cfrTempDirNames();

        Optional<String> result = new CfrDecompiler().decompile(null, "ignored".getBytes(StandardCharsets.UTF_8));

        Assert.assertTrue(result.isEmpty());
        Assert.assertEquals(cfrTempDirNames(), before);
    }

    @Test
    public void testRejectsMalformedClassName() {
        Set<String> before = cfrTempDirNames();

        Optional<String> result = new CfrDecompiler()
                .decompile("path/traversal", "ignored".getBytes(StandardCharsets.UTF_8));

        Assert.assertTrue(result.isEmpty());
        Assert.assertEquals(cfrTempDirNames(), before);
    }

    @Test
    public void testRejectsGarbageBytes() {
        Optional<String> result = new CfrDecompiler()
                .decompile("test.NotAClass", "not a class file".getBytes(StandardCharsets.UTF_8));

        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void testRejectsEmptyBytes() {
        Optional<String> result = new CfrDecompiler().decompile("test.Empty", new byte[0]);

        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void testCleansUpTempDirOnSuccess() {
        byte[] classBytes = InMemoryJavaCompiler.compileTrivialClass("test.CleanupOnSuccess");
        Set<String> before = cfrTempDirNames();

        new CfrDecompiler().decompile("test.CleanupOnSuccess", classBytes);

        Assert.assertEquals(cfrTempDirNames(), before);
    }

    @Test
    public void testCleansUpTempDirOnFailure() {
        Set<String> before = cfrTempDirNames();

        new CfrDecompiler().decompile("test.CleanupOnFailure", "not a class file".getBytes(StandardCharsets.UTF_8));

        Assert.assertEquals(cfrTempDirNames(), before);
    }

    private static Set<String> cfrTempDirNames() {
        Path tempDir = Path.of(System.getProperty("java.io.tmpdir"));
        try (Stream<Path> entries = Files.list(tempDir)) {
            return entries
                    .filter(Files::isDirectory)
                    .map(path -> path.getFileName().toString())
                    .filter(name -> name.startsWith("cfr-"))
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
