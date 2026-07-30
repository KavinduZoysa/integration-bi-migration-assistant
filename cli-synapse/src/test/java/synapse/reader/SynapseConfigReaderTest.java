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
package synapse.reader;

import org.testng.annotations.Test;
import synapse.converter.TestUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Verifies that {@link SynapseConfigReader#collectArtifactFiles} recurses sub-folders for {@code .xml}
 * artifacts while skipping the {@code artifact.xml} CAR/Micro Integrator project descriptor.
 */
public class SynapseConfigReaderTest {

    @Test
    public void ignoresArtifactDescriptorWhileCollecting() throws IOException {
        Path project = Files.createTempDirectory("synapse-collect-test");
        try {
            Files.writeString(project.resolve("api.xml"), "<api/>");
            Files.writeString(project.resolve("artifact.xml"), "<artifacts/>");
            Path nested = Files.createDirectories(project.resolve("sub"));
            Files.writeString(nested.resolve("sequence.xml"), "<sequence/>");
            // Case-insensitive: an upper-cased descriptor is skipped too.
            Files.writeString(nested.resolve("ARTIFACT.XML"), "<artifacts/>");

            List<File> collected = SynapseConfigReader.collectArtifactFiles(project.toString());
            List<String> names = collected.stream().map(File::getName).toList();

            assertTrue(names.contains("api.xml"), "expected api.xml to be collected");
            assertTrue(names.contains("sequence.xml"), "expected nested sequence.xml to be collected");
            assertFalse(names.contains("artifact.xml"), "artifact.xml descriptor must be skipped");
            assertFalse(names.contains("ARTIFACT.XML"), "artifact.xml descriptor must be skipped case-insensitively");
            assertEquals(collected.size(), 2, "only the two real artifacts should be collected");
        } finally {
            TestUtils.deleteDirectory(project);
        }
    }
}
