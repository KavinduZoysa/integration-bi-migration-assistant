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

import synapse.converter.AnalysisResult.SequenceMetadata;
import synapse.converter.DependencyGraph.ArtifactDescriptor;
import synapse.converter.DependencyGraph.ArtifactNode;
import synapse.model.Synapse.Api;
import synapse.model.Synapse.Kind;
import synapse.model.Synapse.Sequence;
import synapse.model.Synapse.SynapseNode;
import synapse.reader.SynapseConfigReader;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Phase 1 of a Synapse migration run: parses every artifact once to gather the cross-artifact facts the
 * conversion phase (phase 2) needs, and returns them as an immutable {@link AnalysisResult}.
 *
 * <p>It produces two things from that single parse pass:
 * <ul>
 *   <li>{@link SequenceMetadata} for each {@code <sequence>} — whether it responds and whether it sets a
 *       payload — with those two flags propagated transitively across sequence-call chains, so a
 *       sequence that only reaches a respond / payload through the sequences it calls is still
 *       marked;</li>
 *   <li>the project {@link DependencyGraph} — an artifact-level DAG whose edges are the
 *       {@code <sequence key="..."/>} references gathered by {@link SequenceReferenceCollector}. Cyclic
 *       and unresolved dependencies are logged as warnings; the graph is still returned so migration can
 *       proceed on a best-effort order.</li>
 * </ul>
 *
 * <p>This is deliberately the single place that walks the whole project up front. New artifact kinds
 * (endpoints, proxies, ...) extend {@link #describeArtifact} and {@link SequenceReferenceCollector}
 * without touching phase 2.
 */
final class ProjectAnalyzer {

    private static final Logger LOGGER = Logger.getLogger(ProjectAnalyzer.class.getName());

    private ProjectAnalyzer() {
    }

    static AnalysisResult analyze(List<File> artifactFiles) {
        Map<String, SequenceMetadata> metadata = new HashMap<>();
        List<ArtifactDescriptor> descriptors = new ArrayList<>();
        for (File artifact : artifactFiles) {
            for (SynapseNode node : SynapseConfigReader.parse(artifact)) {
                describeArtifact(node, artifact).ifPresent(descriptors::add);
                if (node instanceof Sequence sequence) {
                    SequenceMetadata sequenceMetadata = buildSequenceMetadata(sequence);
                    metadata.put(sequenceMetadata.name(), sequenceMetadata);
                }
            }
        }
        propagateRespond(metadata);
        propagatePayloadFactory(metadata);

        DependencyGraph dependencyGraph = DependencyGraph.build(descriptors);
        logDependencyWarnings(dependencyGraph);
        return new AnalysisResult(metadata, dependencyGraph);
    }

    private static Optional<ArtifactDescriptor> describeArtifact(SynapseNode node, File sourceFile) {
        return switch (node) {
            case Api api -> Optional.of(new ArtifactDescriptor(Kind.API, api.name(), sourceFile,
                    SequenceReferenceCollector.collect(api)));
            case Sequence sequence -> Optional.of(new ArtifactDescriptor(Kind.SEQUENCE, sequence.name(), sourceFile,
                    SequenceReferenceCollector.collect(sequence)));
            default -> Optional.empty();
        };
    }

    private static void logDependencyWarnings(DependencyGraph dependencyGraph) {
        for (List<ArtifactNode> cycle : dependencyGraph.cycles()) {
            LOGGER.warning(() -> "Cyclic dependency detected among Synapse artifacts: " + describeCycle(cycle)
                    + ". Migration will proceed on a best-effort order.");
        }
        if (!dependencyGraph.unresolvedReferences().isEmpty()) {
            LOGGER.warning(() -> "Referenced sequences were not found in the project; their dependency edges "
                    + "were skipped: " + String.join(", ", dependencyGraph.unresolvedReferences()));
        }
    }

    private static String describeCycle(List<ArtifactNode> cycle) {
        List<String> names = new ArrayList<>();
        for (ArtifactNode node : cycle) {
            names.add(node.name());
        }
        return String.join(" -> ", names) + " -> " + names.get(0);
    }

    private static SequenceMetadata buildSequenceMetadata(Sequence sequence) {
        boolean containsRespond = false;
        boolean containsPayloadFactory = false;
        for (SynapseNode mediator : sequence.mediators()) {
            if (mediator.kind() == Kind.RESPOND) {
                containsRespond = true;
            } else if (mediator.kind() == Kind.PAYLOAD_FACTORY) {
                containsPayloadFactory = true;
            }
        }
        List<String> referencedSequences = new ArrayList<>(SequenceReferenceCollector.collect(sequence));
        return new SequenceMetadata(sequence.name(), containsRespond, containsPayloadFactory,
                referencedSequences);
    }

    private static void propagateRespond(Map<String, SequenceMetadata> metadata) {
        boolean changed = true;
        while (changed) {
            changed = false;
            for (String name : new ArrayList<>(metadata.keySet())) {
                SequenceMetadata sequenceMetadata = metadata.get(name);
                if (sequenceMetadata.containsRespond()) {
                    continue;
                }
                for (String referenced : sequenceMetadata.referencedSequences()) {
                    SequenceMetadata referencedMetadata = metadata.get(referenced);
                    if (referencedMetadata != null && referencedMetadata.containsRespond()) {
                        metadata.put(name, new SequenceMetadata(name, true,
                                sequenceMetadata.containsPayloadFactory(),
                                sequenceMetadata.referencedSequences()));
                        changed = true;
                        break;
                    }
                }
            }
        }
    }

    private static void propagatePayloadFactory(Map<String, SequenceMetadata> metadata) {
        boolean changed = true;
        while (changed) {
            changed = false;
            for (String name : new ArrayList<>(metadata.keySet())) {
                SequenceMetadata sequenceMetadata = metadata.get(name);
                if (sequenceMetadata.containsPayloadFactory()) {
                    continue;
                }
                for (String referenced : sequenceMetadata.referencedSequences()) {
                    SequenceMetadata referencedMetadata = metadata.get(referenced);
                    if (referencedMetadata != null && referencedMetadata.containsPayloadFactory()) {
                        metadata.put(name, new SequenceMetadata(name, sequenceMetadata.containsRespond(),
                                true, sequenceMetadata.referencedSequences()));
                        changed = true;
                        break;
                    }
                }
            }
        }
    }
}
