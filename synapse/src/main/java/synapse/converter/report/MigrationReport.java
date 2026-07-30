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
package synapse.converter.report;

import synapse.converter.ConversionContext.UnsupportedEntry;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Renders the unsupported Synapse constructs collected during a migration into a Markdown report. Each
 * entry carries the source file and the original Synapse code, mirroring the inline to-do comments left
 * in the generated Ballerina so a reader can find and migrate every gap in one place.
 */
public final class MigrationReport {

    private MigrationReport() {
    }

    public static String render(List<UnsupportedEntry> entries) {
        boolean single = entries.size() == 1;
        StringBuilder report = new StringBuilder();
        report.append("# Synapse to Ballerina migration report\n\n");
        report.append(entries.size()).append(single ? " Synapse construct" : " Synapse constructs")
                .append(single ? " could not be automatically converted and was" : " could not be automatically "
                        + "converted and were")
                .append(" left as TODOs in the generated code. ")
                .append("Each entry shows the source file and the original Synapse code; review and migrate ")
                .append("them manually.\n");

        Map<String, List<UnsupportedEntry>> byCategory = new LinkedHashMap<>();
        for (UnsupportedEntry entry : entries) {
            byCategory.computeIfAbsent(entry.category(), key -> new ArrayList<>()).add(entry);
        }

        for (Map.Entry<String, List<UnsupportedEntry>> group : byCategory.entrySet()) {
            List<UnsupportedEntry> groupEntries = group.getValue();
            report.append("\n## ").append(group.getKey()).append(" (").append(groupEntries.size()).append(")\n");
            for (UnsupportedEntry entry : groupEntries) {
                report.append("\n### `<").append(entry.tag()).append(">`");
                if (!entry.file().isEmpty()) {
                    report.append(" — ").append(entry.file());
                }
                report.append("\n\n").append(entry.detail()).append("\n\n");
                report.append("```xml\n").append(entry.rawXml()).append("\n```\n");
            }
        }
        return report.toString();
    }
}
