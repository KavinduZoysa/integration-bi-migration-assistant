# Synapse to Ballerina migration report

1 Synapse construct could not be automatically converted and was left as TODOs in the generated code. Each entry shows the source file and the original Synapse code; review and migrate them manually.

## Unsupported inbound endpoint parameter (1)

### `<parameter>` — inboundEndpoint.xml

Inbound endpoint 'RemoteFileInbound' parameter 'transport.vfs.FileURI' uses the "ftp" scheme, which has no generated Ballerina listener equivalent yet (file:Listener only supports local paths); manual conversion required.

```xml
<parameter name="transport.vfs.FileURI">ftp://user:pass@host/in</parameter>
```
