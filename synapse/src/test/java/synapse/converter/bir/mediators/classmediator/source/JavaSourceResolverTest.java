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

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JavaSourceResolverTest {

    private static final String CLASS_NAME = "test.Greeter";

    @Test
    public void testSourceRootTakesPrecedence() {
        Path root = TestFixtures.newTempDir("source-root-");
        Path archiveDir = TestFixtures.newTempDir("archive-");
        try {
            TestFixtures.writeFile(root, "test/Greeter.java", "FROM_SOURCE_ROOT");
            Path archive = TestFixtures.writeJar(archiveDir.resolve("mediator.jar"), Map.of(
                    "test/Greeter.java", "FROM_SOURCES_JAR".getBytes(StandardCharsets.UTF_8),
                    "test/Greeter.class", "IGNORED".getBytes(StandardCharsets.UTF_8)));

            JavaSourceResolver resolver = new JavaSourceResolver(
                    List.of(root), List.of(archive), (className, bytes) -> Optional.of("FROM_DECOMPILED"));

            Optional<JavaSource> result = resolver.resolve(CLASS_NAME);

            Assert.assertTrue(result.isPresent());
            Assert.assertEquals(result.get().origin(), JavaSource.Origin.SOURCE_FILE);
            Assert.assertEquals(result.get().source(), "FROM_SOURCE_ROOT");
        } finally {
            TestFixtures.deleteRecursively(root);
            TestFixtures.deleteRecursively(archiveDir);
        }
    }

    @Test
    public void testSourcesJarTakesPrecedenceOverBytecode() {
        Path archiveDir = TestFixtures.newTempDir("archive-");
        boolean[] decompilerInvoked = {false};
        try {
            Path archive = TestFixtures.writeJar(archiveDir.resolve("mediator.jar"), Map.of(
                    "test/Greeter.java", "FROM_SOURCES_JAR".getBytes(StandardCharsets.UTF_8),
                    "test/Greeter.class", "IGNORED".getBytes(StandardCharsets.UTF_8)));

            JavaSourceResolver resolver = new JavaSourceResolver(
                    List.of(), List.of(archive), (className, bytes) -> {
                        decompilerInvoked[0] = true;
                        return Optional.of("FROM_DECOMPILED");
                    });

            Optional<JavaSource> result = resolver.resolve(CLASS_NAME);

            Assert.assertTrue(result.isPresent());
            Assert.assertEquals(result.get().origin(), JavaSource.Origin.SOURCES_JAR);
            Assert.assertEquals(result.get().source(), "FROM_SOURCES_JAR");
            Assert.assertFalse(decompilerInvoked[0]);
        } finally {
            TestFixtures.deleteRecursively(archiveDir);
        }
    }

    @Test
    public void testResolvesFromSourceRoot() {
        Path root = TestFixtures.newTempDir("source-root-");
        try {
            TestFixtures.writeFile(root, "test/Greeter.java", "SOURCE_TEXT");

            JavaSourceResolver resolver = new JavaSourceResolver(List.of(root));

            Optional<JavaSource> result = resolver.resolve(CLASS_NAME);

            Assert.assertTrue(result.isPresent());
            Assert.assertEquals(result.get().origin(), JavaSource.Origin.SOURCE_FILE);
            Assert.assertEquals(result.get().source(), "SOURCE_TEXT");
        } finally {
            TestFixtures.deleteRecursively(root);
        }
    }

    @Test
    public void testResolvesFromSourcesJar() {
        Path archiveDir = TestFixtures.newTempDir("archive-");
        try {
            Path archive = TestFixtures.writeJar(archiveDir.resolve("mediator.jar"),
                    Map.of("test/Greeter.java", "SOURCES_JAR_TEXT".getBytes(StandardCharsets.UTF_8)));

            JavaSourceResolver resolver = new JavaSourceResolver(List.of(), List.of(archive), Decompiler.NONE);

            Optional<JavaSource> result = resolver.resolve(CLASS_NAME);

            Assert.assertTrue(result.isPresent());
            Assert.assertEquals(result.get().origin(), JavaSource.Origin.SOURCES_JAR);
            Assert.assertEquals(result.get().source(), "SOURCES_JAR_TEXT");
        } finally {
            TestFixtures.deleteRecursively(archiveDir);
        }
    }

    @Test
    public void testResolvesFromBytecodeJar() {
        Path archiveDir = TestFixtures.newTempDir("archive-");
        byte[] classBytes = "CLASS_BYTES".getBytes(StandardCharsets.UTF_8);
        byte[][] receivedBytes = new byte[1][];
        try {
            Path archive = TestFixtures.writeJar(
                    archiveDir.resolve("mediator.jar"), Map.of("test/Greeter.class", classBytes));

            JavaSourceResolver resolver = new JavaSourceResolver(
                    List.of(), List.of(archive), (className, bytes) -> {
                        receivedBytes[0] = bytes;
                        return Optional.of("DECOMPILED_TEXT");
                    });

            Optional<JavaSource> result = resolver.resolve(CLASS_NAME);

            Assert.assertTrue(result.isPresent());
            Assert.assertEquals(result.get().origin(), JavaSource.Origin.DECOMPILED);
            Assert.assertEquals(result.get().source(), "DECOMPILED_TEXT");
            Assert.assertEquals(receivedBytes[0], classBytes);
        } finally {
            TestFixtures.deleteRecursively(archiveDir);
        }
    }

    @Test
    public void testFallsThroughSourceRoots() {
        Path rootA = TestFixtures.newTempDir("source-root-a-");
        Path rootB = TestFixtures.newTempDir("source-root-b-");
        try {
            TestFixtures.writeFile(rootB, "test/Greeter.java", "FROM_ROOT_B");

            JavaSourceResolver resolver = new JavaSourceResolver(List.of(rootA, rootB));

            Optional<JavaSource> result = resolver.resolve(CLASS_NAME);

            Assert.assertTrue(result.isPresent());
            Assert.assertEquals(result.get().origin(), JavaSource.Origin.SOURCE_FILE);
            Assert.assertEquals(result.get().source(), "FROM_ROOT_B");
        } finally {
            TestFixtures.deleteRecursively(rootA);
            TestFixtures.deleteRecursively(rootB);
        }
    }

    @Test
    public void testFallsThroughSourcesJars() {
        Path archiveDir = TestFixtures.newTempDir("archive-");
        try {
            Path archiveA = TestFixtures.writeJar(archiveDir.resolve("a.jar"),
                    Map.of("other/Entry.java", "UNRELATED".getBytes(StandardCharsets.UTF_8)));
            Path archiveB = TestFixtures.writeJar(archiveDir.resolve("b.jar"),
                    Map.of("test/Greeter.java", "FROM_ARCHIVE_B".getBytes(StandardCharsets.UTF_8)));

            JavaSourceResolver resolver = new JavaSourceResolver(
                    List.of(), List.of(archiveA, archiveB), Decompiler.NONE);

            Optional<JavaSource> result = resolver.resolve(CLASS_NAME);

            Assert.assertTrue(result.isPresent());
            Assert.assertEquals(result.get().origin(), JavaSource.Origin.SOURCES_JAR);
            Assert.assertEquals(result.get().source(), "FROM_ARCHIVE_B");
        } finally {
            TestFixtures.deleteRecursively(archiveDir);
        }
    }

    @Test
    public void testFallsThroughBytecodeJars() {
        Path archiveDir = TestFixtures.newTempDir("archive-");
        try {
            Path archiveA = TestFixtures.writeJar(archiveDir.resolve("a.jar"),
                    Map.of("other/Entry.class", "UNRELATED".getBytes(StandardCharsets.UTF_8)));
            Path archiveB = TestFixtures.writeJar(archiveDir.resolve("b.jar"),
                    Map.of("test/Greeter.class", "TARGET_BYTES".getBytes(StandardCharsets.UTF_8)));

            JavaSourceResolver resolver = new JavaSourceResolver(
                    List.of(), List.of(archiveA, archiveB), (className, bytes) -> Optional.of("FROM_ARCHIVE_B"));

            Optional<JavaSource> result = resolver.resolve(CLASS_NAME);

            Assert.assertTrue(result.isPresent());
            Assert.assertEquals(result.get().origin(), JavaSource.Origin.DECOMPILED);
            Assert.assertEquals(result.get().source(), "FROM_ARCHIVE_B");
        } finally {
            TestFixtures.deleteRecursively(archiveDir);
        }
    }

    @Test
    public void testFallsThroughOnDecompileFailure() {
        Path archiveDir = TestFixtures.newTempDir("archive-");
        byte[] bytesA = "A".getBytes(StandardCharsets.UTF_8);
        byte[] bytesB = "B".getBytes(StandardCharsets.UTF_8);
        try {
            Path archiveA = TestFixtures.writeJar(archiveDir.resolve("a.jar"), Map.of("test/Greeter.class", bytesA));
            Path archiveB = TestFixtures.writeJar(archiveDir.resolve("b.jar"), Map.of("test/Greeter.class", bytesB));

            JavaSourceResolver resolver = new JavaSourceResolver(
                    List.of(), List.of(archiveA, archiveB), (className, bytes) ->
                            Arrays.equals(bytes, bytesA) ? Optional.empty() : Optional.of("FROM_B"));

            Optional<JavaSource> result = resolver.resolve(CLASS_NAME);

            Assert.assertTrue(result.isPresent());
            Assert.assertEquals(result.get().origin(), JavaSource.Origin.DECOMPILED);
            Assert.assertEquals(result.get().source(), "FROM_B");
        } finally {
            TestFixtures.deleteRecursively(archiveDir);
        }
    }

    @Test
    public void testNoMatchReturnsEmpty() {
        Path root = TestFixtures.newTempDir("source-root-");
        Path archiveDir = TestFixtures.newTempDir("archive-");
        try {
            Path archive = TestFixtures.writeJar(archiveDir.resolve("mediator.jar"),
                    Map.of("other/Entry.java", "UNRELATED".getBytes(StandardCharsets.UTF_8)));

            JavaSourceResolver resolver = new JavaSourceResolver(List.of(root), List.of(archive), Decompiler.NONE);

            Assert.assertTrue(resolver.resolve(CLASS_NAME).isEmpty());
        } finally {
            TestFixtures.deleteRecursively(root);
            TestFixtures.deleteRecursively(archiveDir);
        }
    }

    @Test
    public void testSkipsCorruptArchive() {
        Path archiveDir = TestFixtures.newTempDir("archive-");
        try {
            Path garbage = TestFixtures.writeBytes(
                    archiveDir.resolve("garbage.jar"), "not a zip file".getBytes(StandardCharsets.UTF_8));
            Path goodArchive = TestFixtures.writeJar(archiveDir.resolve("good.jar"),
                    Map.of("test/Greeter.java", "FROM_GOOD_ARCHIVE".getBytes(StandardCharsets.UTF_8)));

            JavaSourceResolver resolver = new JavaSourceResolver(
                    List.of(), List.of(garbage, goodArchive), Decompiler.NONE);

            Optional<JavaSource> result = resolver.resolve(CLASS_NAME);

            Assert.assertTrue(result.isPresent());
            Assert.assertEquals(result.get().source(), "FROM_GOOD_ARCHIVE");
        } finally {
            TestFixtures.deleteRecursively(archiveDir);
        }
    }

    @Test
    public void testSkipsDirectoryArchive() {
        Path directoryArchive = TestFixtures.newTempDir("not-a-jar-");
        Path archiveDir = TestFixtures.newTempDir("archive-");
        try {
            Path goodArchive = TestFixtures.writeJar(archiveDir.resolve("good.jar"),
                    Map.of("test/Greeter.java", "FROM_GOOD_ARCHIVE".getBytes(StandardCharsets.UTF_8)));

            JavaSourceResolver resolver = new JavaSourceResolver(
                    List.of(), List.of(directoryArchive, goodArchive), Decompiler.NONE);

            Optional<JavaSource> result = resolver.resolve(CLASS_NAME);

            Assert.assertTrue(result.isPresent());
            Assert.assertEquals(result.get().source(), "FROM_GOOD_ARCHIVE");
        } finally {
            TestFixtures.deleteRecursively(directoryArchive);
            TestFixtures.deleteRecursively(archiveDir);
        }
    }

    @Test
    public void testNoDecompilerReturnsEmpty() {
        Path archiveDir = TestFixtures.newTempDir("archive-");
        try {
            Path archive = TestFixtures.writeJar(archiveDir.resolve("mediator.jar"),
                    Map.of("test/Greeter.class", "CLASS_BYTES".getBytes(StandardCharsets.UTF_8)));

            JavaSourceResolver resolver = new JavaSourceResolver(List.of(), List.of(archive), Decompiler.NONE);

            Assert.assertTrue(resolver.resolve(CLASS_NAME).isEmpty());
        } finally {
            TestFixtures.deleteRecursively(archiveDir);
        }
    }

    @Test
    public void testRejectsNullClassName() {
        Path root = TestFixtures.newTempDir("source-root-");
        try {
            TestFixtures.writeFile(root, "test/Greeter.java", "SHOULD_NOT_BE_READ");

            JavaSourceResolver resolver = new JavaSourceResolver(
                    List.of(root), List.of(), (className, bytes) -> {
                        throw new AssertionError("decompiler must not run for a null class name");
                    });

            Assert.assertTrue(resolver.resolve(null).isEmpty());
        } finally {
            TestFixtures.deleteRecursively(root);
        }
    }

    @Test
    public void testRejectsMalformedClassNames() {
        JavaSourceResolver resolver = new JavaSourceResolver(
                List.of(), List.of(), (className, bytes) -> {
                    throw new AssertionError("decompiler must not run for a rejected class name");
                });

        List<String> invalidNames = List.of(
                "", "1Leading", "trailing.", "has space", "path/traversal", "../Evil", "com..Double");

        for (String name : invalidNames) {
            Assert.assertTrue(resolver.resolve(name).isEmpty(), "expected rejection for: " + name);
        }
    }
}
