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
 *  KIND, either express or implied. See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package synapse.model;

import synapse.model.DependencyGraph.ArtifactNode;
import synapse.model.Synapse.Api;
import synapse.model.Synapse.FaultSequence;
import synapse.model.Synapse.FaultSequenceRef;
import synapse.model.Synapse.InSequence;
import synapse.model.Synapse.Kind;
import synapse.model.Synapse.Resource;
import synapse.model.Synapse.Sequence;
import synapse.model.Synapse.SequenceMediator;
import synapse.model.Synapse.SynapseNode;
import synapse.model.Synapse.Unsupported;
import synapse.reader.SynapseConfigReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Resolves the artifacts that a given {@link ArtifactNode} depends on.
 *
 * <p>Only sequence dependencies are considered for now: an artifact's mediator tree is walked for
 * {@code <sequence key="..."/>} references, and each key is matched against the known sequence
 * artifacts. References that match no known sequence are reported as unresolved.
 */
public class DependencyResolver {

    private final Map<String, ArtifactNode> sequencesByName;
    private final Set<ArtifactNode> unresolvedNodes = new LinkedHashSet<>();

    public DependencyResolver(List<ArtifactNode> nodes) {
        this.sequencesByName = new HashMap<>();
        for (ArtifactNode node : nodes) {
            if (node.kind() == Kind.SEQUENCE) {
                sequencesByName.put(node.name(), node);
            }
        }
    }

    public List<ArtifactNode> resolve(ArtifactNode artifact) {
        Set<String> sequenceKeys = new LinkedHashSet<>();
        collectSequenceKeys(findArtifact(artifact), sequenceKeys);

        List<ArtifactNode> resolved = new ArrayList<>();
        for (String key : sequenceKeys) {
            ArtifactNode dependency = sequencesByName.get(key);
            if (dependency != null) {
                resolved.add(dependency);
            } else {
                unresolvedNodes.add(new ArtifactNode(Kind.SEQUENCE + ":" + key, key, Kind.SEQUENCE, null));
            }
        }
        return resolved;
    }

    public Set<ArtifactNode> unresolvedNodes() {
        return unresolvedNodes;
    }

    public static SynapseNode findArtifact(ArtifactNode artifact) {
        for (SynapseNode node : SynapseConfigReader.parse(artifact.file().toFile())) {
            if (matches(node, artifact)) {
                return node;
            }
        }
        throw new IllegalStateException("Artifact '" + artifact.id() + "' not found in " + artifact.file());
    }

    private static boolean matches(SynapseNode node, ArtifactNode artifact) {
        return switch (node) {
            case Api api -> artifact.kind() == Kind.API && api.name().equals(artifact.name());
            case Sequence sequence -> artifact.kind() == Kind.SEQUENCE
                    && sequence.name().equals(artifact.name());
            default -> false;
        };
    }

    private void collectSequenceKeys(SynapseNode node, Set<String> sequenceKeys) {
        switch (node) {
            case Api api -> api.resources().forEach(resource -> collectSequenceKeys(resource, sequenceKeys));
            case Resource resource -> {
                if (resource.inSequence() != null) {
                    collectSequenceKeys(resource.inSequence(), sequenceKeys);
                }
                switch (resource.faultSequenceRef()) {
                    case FaultSequenceRef.KeyRef(String key) -> sequenceKeys.add(key);
                    case FaultSequenceRef.Inline(FaultSequence faultSequence) ->
                            collectSequenceKeys(faultSequence, sequenceKeys);
                    case FaultSequenceRef.None ignored -> { }
                }
                addImplicitDefaultFaultSequenceKey(resource, sequenceKeys);
            }
            case InSequence inSequence ->
                    inSequence.mediators().forEach(mediator -> collectSequenceKeys(mediator, sequenceKeys));
            case FaultSequence faultSequence ->
                    faultSequence.mediators().forEach(mediator -> collectSequenceKeys(mediator, sequenceKeys));
            case Sequence sequence ->
                    sequence.mediators().forEach(mediator -> collectSequenceKeys(mediator, sequenceKeys));
            case SequenceMediator sequenceMediator -> sequenceKeys.add(sequenceMediator.key());
            case Unsupported unsupported ->
                    unsupported.children().forEach(child -> collectSequenceKeys(child, sequenceKeys));
            default -> { }
        }
    }

    // A resource with no faultSequence at all, or a faultSequence key that names no known sequence,
    // implicitly depends on the project's "fault" sequence when one exists.
    private void addImplicitDefaultFaultSequenceKey(Resource resource, Set<String> sequenceKeys) {
        Predicate<String> sequenceExists = sequencesByName::containsKey;
        if (resource.fallsBackToDefaultFaultSequence(sequenceExists)
                && sequencesByName.containsKey(Synapse.DEFAULT_FAULT_SEQUENCE_KEY)) {
            sequenceKeys.add(Synapse.DEFAULT_FAULT_SEQUENCE_KEY);
        }
    }
}
