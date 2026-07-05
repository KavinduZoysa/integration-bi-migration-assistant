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

import synapse.model.Synapse.Kind;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A directed acyclic graph of the Synapse artifacts in a project and the dependencies between them,
 * built during the analysis phase (phase 1) and carried on the {@link AnalysisResult}.
 *
 * <p>Each XML artifact ({@code <api>}, {@code <sequence>}, and future kinds) is a {@link ArtifactNode};
 * non-XML files such as {@code .xsd} / {@code .xslt} are resources, not nodes. An edge <em>A depends on
 * B</em> means artifact {@code A} references sequence {@code B} via {@code <sequence key="B"/>} — the
 * only kind of dependency modelled, so every edge target is a sequence. A reference to a sequence not
 * present in the project resolves to no edge and is recorded in {@link #unresolvedReferences()}.
 *
 * <p>{@link #topologicalOrder()} lists the artifacts leaf-first: an artifact appears only after every
 * artifact it depends on, which is the order a "migrate from the leaves" conversion would follow.
 * {@link #fileConversionOrder()} projects that ordering onto the source files. The graph is expected to
 * be acyclic; any cycle found is reported in {@link #cycles()} and the offending back-edge is dropped
 * so a best-effort order is still produced (callers warn on {@link #hasCycles()}).
 */
public final class DependencyGraph {

    private final List<ArtifactNode> nodes;
    private final Map<ArtifactNode, List<ArtifactNode>> dependencies;
    private final List<ArtifactNode> topologicalOrder;
    private final List<List<ArtifactNode>> cycles;
    private final Set<String> unresolvedReferences;

    private DependencyGraph(List<ArtifactNode> nodes, Map<ArtifactNode, List<ArtifactNode>> dependencies,
                            List<ArtifactNode> topologicalOrder, List<List<ArtifactNode>> cycles,
                            Set<String> unresolvedReferences) {
        this.nodes = List.copyOf(nodes);
        Map<ArtifactNode, List<ArtifactNode>> immutableDependencies = new LinkedHashMap<>();
        dependencies.forEach((node, deps) -> immutableDependencies.put(node, List.copyOf(deps)));
        this.dependencies = Collections.unmodifiableMap(immutableDependencies);
        this.topologicalOrder = List.copyOf(topologicalOrder);
        List<List<ArtifactNode>> immutableCycles = new ArrayList<>();
        cycles.forEach(cycle -> immutableCycles.add(List.copyOf(cycle)));
        this.cycles = Collections.unmodifiableList(immutableCycles);
        this.unresolvedReferences = Collections.unmodifiableSet(new LinkedHashSet<>(unresolvedReferences));
    }

    /**
     * Builds the graph from one descriptor per artifact. Descriptors are assumed to be in a stable order
     * (e.g. by file path), which the topological order preserves among artifacts that do not depend on
     * one another, keeping the result deterministic.
     */
    public static DependencyGraph build(List<ArtifactDescriptor> descriptors) {
        List<ArtifactNode> nodes = new ArrayList<>();
        Map<String, ArtifactNode> sequenceIndex = new LinkedHashMap<>();
        for (ArtifactDescriptor descriptor : descriptors) {
            ArtifactNode node = new ArtifactNode(descriptor.kind() + ":" + descriptor.name(),
                    descriptor.kind(), descriptor.name(), descriptor.sourceFile());
            nodes.add(node);
            if (descriptor.kind() == Kind.SEQUENCE) {
                sequenceIndex.put(descriptor.name(), node);
            }
        }

        Map<ArtifactNode, List<ArtifactNode>> dependencies = new LinkedHashMap<>();
        Set<String> unresolvedReferences = new LinkedHashSet<>();
        for (int i = 0; i < nodes.size(); i++) {
            ArtifactNode node = nodes.get(i);
            List<ArtifactNode> deps = new ArrayList<>();
            for (String referencedArtifact : descriptors.get(i).referencedArtifacts()) {
                ArtifactNode target = sequenceIndex.get(referencedArtifact);
                if (target == null) {
                    unresolvedReferences.add(referencedArtifact);
                } else if (!target.equals(node) && !deps.contains(target)) {
                    deps.add(target);
                }
            }
            dependencies.put(node, deps);
        }

        TopologicalSorter sorter = new TopologicalSorter(dependencies);
        sorter.sort(nodes);
        return new DependencyGraph(nodes, dependencies, sorter.order, sorter.cycles, unresolvedReferences);
    }

    public List<ArtifactNode> nodes() {
        return nodes;
    }

    public List<ArtifactNode> dependencies(ArtifactNode node) {
        return dependencies.getOrDefault(node, List.of());
    }

    /**
     * The artifacts leaf-first: every artifact appears after all the artifacts it depends on.
     */
    public List<ArtifactNode> topologicalOrder() {
        return topologicalOrder;
    }

    /**
     * The distinct source files in leaf-first order — each file positioned at its last-appearing
     * artifact in {@link #topologicalOrder()}, so a file follows every file it depends on (barring a
     * cross-file cycle). This is the order a leaf-first conversion phase would read the files in.
     */
    public List<File> fileConversionOrder() {
        Map<File, Integer> lastPosition = new LinkedHashMap<>();
        for (int position = 0; position < topologicalOrder.size(); position++) {
            lastPosition.put(topologicalOrder.get(position).sourceFile(), position);
        }
        return lastPosition.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .toList();
    }

    public List<List<ArtifactNode>> cycles() {
        return cycles;
    }

    public boolean hasCycles() {
        return !cycles.isEmpty();
    }

    /**
     * The {@code key}s of {@code <sequence key="..."/>} references whose target sequence is not present
     * in the project; each yielded no edge.
     */
    public Set<String> unresolvedReferences() {
        return unresolvedReferences;
    }

    /**
     * A node in the graph: one top-level Synapse artifact.
     *
     * @param id         identifier unique across the project ({@code kind:name})
     * @param kind       the artifact kind ({@code API}, {@code SEQUENCE}, ...)
     * @param name       the artifact name
     * @param sourceFile the XML file the artifact was read from
     */
    public record ArtifactNode(String id, Kind kind, String name, File sourceFile) {
    }

    /**
     * The input to {@link #build(List)}: an artifact's identity, its source file, and the sequences it
     * references (its outgoing edges, gathered by {@link SequenceReferenceCollector}).
     *
     * @param kind                the artifact kind
     * @param name                the artifact name
     * @param sourceFile          the XML file the artifact was read from
     * @param referencedArtifacts the {@code key}s of the artifacts this artifact depends on
     */
    public record ArtifactDescriptor(Kind kind, String name, File sourceFile, Set<String> referencedArtifacts) {
    }

    /**
     * Depth-first topological sort producing a leaf-first {@link #order} and collecting every cycle it
     * meets. A dependency already on the current path (a back-edge) is a cycle: it is recorded and not
     * followed, so the traversal still visits every node and yields a best-effort order.
     */
    private static final class TopologicalSorter {

        private static final int VISITING = 1;
        private static final int VISITED = 2;

        private final Map<ArtifactNode, List<ArtifactNode>> dependencies;
        private final Map<ArtifactNode, Integer> state = new HashMap<>();
        private final List<ArtifactNode> path = new ArrayList<>();
        private final List<ArtifactNode> order = new ArrayList<>();
        private final List<List<ArtifactNode>> cycles = new ArrayList<>();

        private TopologicalSorter(Map<ArtifactNode, List<ArtifactNode>> dependencies) {
            this.dependencies = dependencies;
        }

        private void sort(List<ArtifactNode> nodes) {
            for (ArtifactNode node : nodes) {
                if (!state.containsKey(node)) {
                    visit(node);
                }
            }
        }

        private void visit(ArtifactNode node) {
            state.put(node, VISITING);
            path.add(node);
            for (ArtifactNode dependency : dependencies.get(node)) {
                Integer dependencyState = state.get(dependency);
                if (dependencyState == null) {
                    visit(dependency);
                } else if (dependencyState == VISITING) {
                    cycles.add(new ArrayList<>(path.subList(path.indexOf(dependency), path.size())));
                }
            }
            state.put(node, VISITED);
            path.remove(path.size() - 1);
            order.add(node);
        }
    }
}
