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

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static org.testng.Assert.assertTrue;

/**
 * Verifies that property scopes with no supported Ballerina translation are surfaced as to-do markers
 * rather than aborting the migration or being silently mis-converted: a {@code remove} action outside
 * the default/synapse scope (transport, axis2), and any scope other than transport, axis2, default or
 * synapse. The conversion still completes, the offending property is left out of the generated code,
 * and the case is recorded both inline (an inline marker) and in {@code migration_report.md}.
 */
public class PropertyScopeTest {

    private static final Path UNSUPPORTED = Path.of("src", "test", "resources", "unsupported-property");

    @Test(dataProvider = "unsupportedCaseProvider")
    public void unsupportedPropertyScopeBecomesTodo(String project) throws IOException {
        Path output = Files.createTempDirectory("synapse-unsupported-property-test");
        try {
            SynapseConverter.migrateSynapse(UNSUPPORTED.resolve(project).toString(), output.toString(),
                    false, false, false, false, Optional.of("testOrg"), Optional.of("pkg"));

            Path report = output.resolve("migration_report.md");
            assertTrue(Files.exists(report), "migration_report.md should be generated for " + project);
            String reportContent = Files.readString(report);
            assertTrue(reportContent.contains("Unsupported property"),
                    "report should list the unsupported property for " + project);

            String mainBal = Files.readString(output.resolve("main.bal"));
            assertTrue(mainBal.contains("// TODO: Unsupported Synapse property"),
                    "main.bal should carry an inline TODO for " + project);
        } finally {
            TestUtils.deleteDirectory(output);
        }
    }

    @DataProvider
    public Object[][] unsupportedCaseProvider() {
        return new Object[][]{
                {"transport-remove"},
                {"axis2-remove"},
                {"unknown-scope"},
        };
    }
}
