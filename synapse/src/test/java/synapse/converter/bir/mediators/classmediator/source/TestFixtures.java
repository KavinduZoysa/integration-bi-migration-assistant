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
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.stream.Stream;

final class TestFixtures {

    private TestFixtures() {
    }

    static Path newTempDir(String prefix) {
        try {
            return Files.createTempDirectory(prefix);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    static void deleteRecursively(Path root) {
        if (root == null || !Files.exists(root)) {
            return;
        }
        try (Stream<Path> walk = Files.walk(root)) {
            walk.sorted(Comparator.reverseOrder()).forEach(path -> {
                try {
                    Files.deleteIfExists(path);
                } catch (IOException e) {
                    // Best-effort cleanup; a leftover temp file here does not affect test correctness.
                }
            });
        } catch (IOException e) {
            // Best-effort cleanup; a leftover temp directory here does not affect test correctness.
        }
    }

    static Path writeFile(Path root, String relativePath, String content) {
        return writeBytes(root.resolve(relativePath), content.getBytes(StandardCharsets.UTF_8));
    }

    static Path writeBytes(Path file, byte[] content) {
        try {
            Files.createDirectories(file.getParent());
            Files.write(file, content);
            return file;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    static Path writeJar(Path jarFile, Map<String, byte[]> entries) {
        try (JarOutputStream jar = new JarOutputStream(Files.newOutputStream(jarFile))) {
            for (Map.Entry<String, byte[]> entry : entries.entrySet()) {
                jar.putNextEntry(new JarEntry(entry.getKey()));
                jar.write(entry.getValue());
                jar.closeEntry();
            }
            return jarFile;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
