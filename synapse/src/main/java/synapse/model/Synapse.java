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

import java.util.List;
import java.util.function.Predicate;

public record Synapse() {

    // The conventional project-level sequence name a resource with no faultSequence of its own falls
    // back to implicitly, if one exists.
    public static final String DEFAULT_FAULT_SEQUENCE_KEY = "fault";

    public record Api(Kind kind, String name, String context, List<SynapseNode> resources)
            implements SynapseNode {
        public Api(String name, String context, List<SynapseNode> resources) {
            this(Kind.API, name, context, resources);
        }
    }

    // matchAnyPath is set when the Synapse resource declares neither 'uri-template' nor 'url-mapping';
    // such a resource matches any path, converted to a Ballerina rest path parameter ([string... path]).
    // faultSequenceKey is the resource's faultSequence="X" attribute (empty when absent): a named
    // reference taking priority over the inline faultSequence element below.
    public record Resource(Kind kind, String methods, String path, boolean matchAnyPath,
                           List<String> queryParams, InSequence inSequence,
                           FaultSequence faultSequence, String faultSequenceKey) implements SynapseNode {
        public Resource(String methods, String path, boolean matchAnyPath, List<String> queryParams,
                        InSequence inSequence, FaultSequence faultSequence, String faultSequenceKey) {
            this(Kind.RESOURCE, methods, path, matchAnyPath, queryParams, inSequence, faultSequence,
                    faultSequenceKey);
        }

        // A faultSequenceKey naming no known sequence, or no faultSequenceKey and no inline
        // faultSequence, means this resource falls back to the project's default "fault" sequence.
        // sequenceExists tells whether a given key names a known sequence, in whichever form the
        // caller has that knowledge available (converted metadata, or a raw artifact lookup).
        public boolean fallsBackToDefaultFaultSequence(Predicate<String> sequenceExists) {
            return faultSequenceKey.isBlank() ? faultSequence == null : !sequenceExists.test(faultSequenceKey);
        }
    }

    // <inSequence> ... </inSequence> -> the request-processing mediator sequence of a resource.
    public record InSequence(Kind kind, List<SynapseNode> mediators) implements SynapseNode {
        public InSequence(List<SynapseNode> mediators) {
            this(Kind.IN_SEQUENCE, mediators);
        }
    }

    // <faultSequence> ... </faultSequence> -> the error-handling mediator sequence of a resource, run
    // when mediating its inSequence fails. Converted to a Ballerina 'on fail' clause.
    public record FaultSequence(Kind kind, List<SynapseNode> mediators) implements SynapseNode {
        public FaultSequence(List<SynapseNode> mediators) {
            this(Kind.FAULT_SEQUENCE, mediators);
        }
    }

    // <sequence name="..." onError="..." description="..."> ... </sequence>
    // -> a named, reusable mediator sequence declared at the top level.
    public record Sequence(Kind kind, String name, String onError, String description,
                           List<SynapseNode> mediators) implements SynapseNode {
        public Sequence(String name, String onError, String description, List<SynapseNode> mediators) {
            this(Kind.SEQUENCE, name, onError, description, mediators);
        }
    }

    // <payloadFactory media-type="json"><format>{"Hello":"World"}</format></payloadFactory>
    // -> sets the response payload to the given format (of the given media type).
    public record PayloadFactory(Kind kind, String mediaType, String format) implements SynapseNode {
        public PayloadFactory(String mediaType, String format) {
            this(Kind.PAYLOAD_FACTORY, mediaType, format);
        }
    }

    // <respond/> -> sends the current message back as the response.
    public record Respond(Kind kind) implements SynapseNode {
        public Respond() {
            this(Kind.RESPOND);
        }
    }

    // <sequence key="name"/> -> invokes the named sequence referenced by 'key'.
    public record SequenceMediator(Kind kind, String key) implements SynapseNode {
        public SequenceMediator(String key) {
            this(Kind.SEQUENCE_MEDIATOR, key);
        }
    }
  
    // <class name="org.example.MyMediator">
    //   <property name="key" value="val"/>
    // </class>
    public record ClassMediator(Kind kind, String className, List<Property> properties) implements SynapseNode {
        public ClassMediator(String className, List<Property> properties) {
            this(Kind.CLASS_MEDIATOR, className, properties);
        }
    }

    // <property name="..." scope="default|transport|axis2|axis2-client" type="string" value="..."
    //           expression="..." action="set|remove"> <om-element/>? </property>
    // -> action "set" (the default) sets a named property (of the given type and scope) to the given
    //    value or expression (mutually exclusive; expression holds a Synapse XPath), or to the inline XML
    //    child element carried in omElement; action "remove" clears it. A present omElement makes the
    //    property an XML (OM) value regardless of the declared type.
    public record Property(Kind kind, String name, SynapseType type, String scope, String value,
                           String expression, String omElement, String action) implements SynapseNode {
        public Property(String name, SynapseType type, String scope, String value, String expression,
                        String omElement, String action) {
            this(Kind.PROPERTY, name, type, scope, value, expression, omElement, action);
        }

        public boolean hasExpression() {
            return expression != null && !expression.isEmpty();
        }

        public boolean hasOmElement() {
            return omElement != null && !omElement.isEmpty();
        }
    }

    // An unsupported mediator captured verbatim so it can be surfaced as a to-do rather than silently
    // dropped. rawXml is the serialized Synapse element; children holds any nested mediators recognised
    // inside a control-flow wrapper (e.g. a <filter>'s <then>/<else>) so they can still be converted.
    public record Unsupported(Kind kind, String tag, String rawXml, List<SynapseNode> children)
            implements SynapseNode {
        public Unsupported(String tag, String rawXml, List<SynapseNode> children) {
            this(Kind.UNSUPPORTED_MEDIATOR, tag, rawXml, children);
        }
    }

    // An unsupported top-level artifact (e.g. <proxy>, <endpoint>) captured verbatim so it can be
    // surfaced in the migration report rather than silently dropped.
    public record UnsupportedArtifact(Kind kind, String tag, String name, String rawXml)
            implements SynapseNode {
        public UnsupportedArtifact(String tag, String name, String rawXml) {
            this(Kind.UNSUPPORTED_ARTIFACT, tag, name, rawXml);
        }
    }

    public interface SynapseNode {
        Kind kind();
    }

    public enum Kind {
        API,
        RESOURCE,
        IN_SEQUENCE,
        FAULT_SEQUENCE,
        SEQUENCE,
        PAYLOAD_FACTORY,
        RESPOND,
        PROPERTY,
        SEQUENCE_MEDIATOR,
        CLASS_MEDIATOR,
        UNSUPPORTED_MEDIATOR,
        UNSUPPORTED_ARTIFACT
    }
}
