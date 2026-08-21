# Synapse to Ballerina migration report

2 Synapse constructs could not be automatically converted and were left as TODOs in the generated code. Each entry shows the source file and the original Synapse code; review and migrate them manually.

## Unsupported mediator (2)

### `<log>` — HttpInboundFaultSeq.xml

Mediator not supported; manual conversion required.

```xml
<log category="ERROR" xmlns="http://ws.apache.org/ns/synapse">
        <message>Inbound error: ${properties.synapse.ERROR_MESSAGE}</message>
    </log>
```

### `<log>` — HttpInboundSeq.xml

Mediator not supported; manual conversion required.

```xml
<log category="INFO" logFullPayload="true" xmlns="http://ws.apache.org/ns/synapse">
        <message>Inbound request received</message>
    </log>
```
