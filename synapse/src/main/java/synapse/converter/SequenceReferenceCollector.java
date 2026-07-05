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

import synapse.model.Synapse.Api;
import synapse.model.Synapse.InSequence;
import synapse.model.Synapse.Resource;
import synapse.model.Synapse.Sequence;
import synapse.model.Synapse.SequenceMediator;
import synapse.model.Synapse.SynapseNode;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Collects the names of the sequences a Synapse artifact depends on: the {@code key} of every
 * {@code <sequence key="..."/>} mediator reachable anywhere inside the given node's subtree.
 *
 * <p>These keys are the outgoing edges of the {@link DependencyGraph} — the sole kind of dependency
 * modelled, since every dependency ultimately targets a sequence (an {@code <api>} reaches sequences
 * through its resources' {@code <inSequence>}, a {@code <sequence>} through its own mediators). The
 * walk dispatches on node type and recurses, so future artifact kinds and nested mediators are picked
 * up by extending the {@code switch} rather than by changing callers.
 */
final class SequenceReferenceCollector {

    private SequenceReferenceCollector() {
    }

    static Set<String> collect(SynapseNode node) {
        Set<String> referencedSequences = new LinkedHashSet<>();
        collectInto(node, referencedSequences);
        return referencedSequences;
    }

    private static void collectInto(SynapseNode node, Set<String> referencedSequences) {
        switch (node) {
            case SequenceMediator sequenceMediator -> referencedSequences.add(sequenceMediator.key());
            case Api api -> api.resources().forEach(resource -> collectInto(resource, referencedSequences));
            case Resource resource -> {
                if (resource.inSequence() != null) {
                    collectInto(resource.inSequence(), referencedSequences);
                }
            }
            case InSequence inSequence ->
                    inSequence.mediators().forEach(mediator -> collectInto(mediator, referencedSequences));
            case Sequence sequence ->
                    sequence.mediators().forEach(mediator -> collectInto(mediator, referencedSequences));
            default -> {
                // Mediators that cannot reach a sequence reference (e.g. <payloadFactory>, <property>,
                // <respond>) contribute no edges.
            }
        }
    }
}
