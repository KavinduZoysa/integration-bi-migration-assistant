# Synapse to Ballerina migration report

1 Synapse construct could not be automatically converted and was left as TODOs in the generated code. Each entry shows the source file and the original Synapse code; review and migrate them manually.

## Unsupported inbound endpoint parameter (1)

### `<parameter>` — inboundEndpoint.xml

Inbound endpoint 'TopicInbound' parameter 'transport.jms.ConnectionFactoryType' is "topic", but the generated jms:Listener always binds to a queue; manual conversion required.

```xml
<parameter name="transport.jms.ConnectionFactoryType">topic</parameter>
```
