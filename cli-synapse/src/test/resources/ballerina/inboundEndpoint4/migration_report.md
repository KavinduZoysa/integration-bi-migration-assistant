# Synapse to Ballerina migration report

1 Synapse construct could not be automatically converted and was left as TODOs in the generated code. Each entry shows the source file and the original Synapse code; review and migrate them manually.

## Unsupported inbound endpoint protocol (1)

### `<inboundEndpoint>` — inboundEndpoint.xml

Inbound endpoint 'JmsInbound' uses protocol="jms", which has no generated Ballerina listener equivalent yet; manual conversion required.

```xml
<inboundEndpoint xmlns="http://ws.apache.org/ns/synapse" name="JmsInbound" protocol="jms" sequence="foo" suspend="false">
    <parameters>
        <parameter name="java.naming.factory.initial">org.apache.activemq.jndi.ActiveMQInitialContextFactory</parameter>
    </parameters>
</inboundEndpoint>
```
