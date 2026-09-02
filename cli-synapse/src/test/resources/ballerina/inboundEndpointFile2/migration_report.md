# Synapse to Ballerina migration report

1 Synapse construct could not be automatically converted and was left as TODOs in the generated code. Each entry shows the source file and the original Synapse code; review and migrate them manually.

## Unsupported inbound endpoint parameter (1)

### `<parameter>` — inboundEndpoint.xml

Inbound endpoint 'FileArchiveInbound' parameter 'transport.vfs.ActionAfterProcess' is not mapped to any Ballerina construct; manual conversion required.

```xml
<parameter name="transport.vfs.ActionAfterProcess">DELETE</parameter>
```
