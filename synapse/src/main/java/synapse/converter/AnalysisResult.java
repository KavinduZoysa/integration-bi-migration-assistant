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

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Immutable product of the analysis phase (phase 1) of a Synapse migration run: the cross-artifact
 * facts gathered up front by {@link ProjectAnalyzer} and consumed, read-only, by the conversion phase
 * (phase 2) through {@link ConversionContext}.
 *
 * <p>It carries:
 * <ul>
 *   <li>the pre-gathered {@link SequenceMetadata} for every {@code <sequence>} — whether a sequence
 *       responds and whether it sets a payload, resolved transitively across sequence-call chains, so
 *       the conversion phase can decide at each call site whether a generated function returns a
 *       response or takes one in;</li>
 *   <li>the project {@link DependencyGraph} — which artifact depends on which sequence — used to order
 *       the migration from the leaves and to surface cyclic / unresolved dependencies.</li>
 * </ul>
 *
 * <p>Keep new cross-artifact analysis facts on this immutable result rather than on the mutable
 * {@link ConversionContext}.
 */
public final class AnalysisResult {

    private final Map<String, SequenceMetadata> sequenceMetadata;
    private final DependencyGraph dependencyGraph;

    AnalysisResult(Map<String, SequenceMetadata> sequenceMetadata, DependencyGraph dependencyGraph) {
        assert sequenceMetadata != null : "sequenceMetadata must not be null";
        assert dependencyGraph != null : "dependencyGraph must not be null";
        this.sequenceMetadata = Map.copyOf(sequenceMetadata);
        this.dependencyGraph = dependencyGraph;
    }

    public Optional<SequenceMetadata> sequenceMetadata(String name) {
        return Optional.ofNullable(sequenceMetadata.get(name));
    }

    public DependencyGraph dependencyGraph() {
        return dependencyGraph;
    }

    /**
     * Whether any sequence in the project sets a payload — holds a {@code <payloadFactory>} directly or
     * reaches one down a call chain. When true the generated {@code functions.bal} references
     * {@code http:Response} and so needs the {@code ballerina/http} import.
     */
    public boolean containsPayloadFactoryAnywhere() {
        return sequenceMetadata.values().stream().anyMatch(SequenceMetadata::containsPayloadFactory);
    }

    // Pre-gathered facts about a <sequence>. containsRespond and containsPayloadFactory are transitive:
    // also true when a referencedSequences entry responds / sets a payload (resolved by propagation in
    // ProjectAnalyzer), so a call site can decide across chains whether to return a response or pass one
    // in. referencedSequences additionally forms the edges of the project dependency graph (see the
    // class javadoc).
    public record SequenceMetadata(String name, boolean containsRespond, boolean containsPayloadFactory,
                                   List<String> referencedSequences) {
    }
}
