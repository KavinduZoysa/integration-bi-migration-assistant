# Synapse to Ballerina migration report

1 Synapse construct could not be automatically converted and was left as TODOs in the generated code. Each entry shows the source file and the original Synapse code; review and migrate them manually.

## Unsupported respond in non-HTTP inbound endpoint (1)

### `<respond>` — inboundEndpoint.xml

Inbound endpoint 'JmsInbound' (protocol="jms") reaches a <respond/> mediator, directly or via a called sequence, but this protocol has no reply transport to respond on; manual conversion required.

```xml
<inboundEndpoint xmlns="http://ws.apache.org/ns/synapse" name="JmsInbound" protocol="jms" sequence="foo" suspend="false">
    <parameters>
        <parameter name="java.naming.factory.initial">org.apache.activemq.jndi.ActiveMQInitialContextFactory</parameter>
        <parameter name="transport.jms.Destination">OrderQueue</parameter>
    </parameters>
</inboundEndpoint>
```
