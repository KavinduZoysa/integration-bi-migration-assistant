# Synapse to Ballerina migration report

1 Synapse construct could not be automatically converted and was left as TODOs in the generated code. Each entry shows the source file and the original Synapse code; review and migrate them manually.

## Unmodeled inbound endpoint TLS configuration (1)

### `<protocol>` — inboundEndpoint.xml

Inbound endpoint 'HttpsInbound' uses protocol="https" but declares no keystore; TLS configuration cannot be built, so the generated listener is a plain, unencrypted http:Listener. Manual conversion required to add TLS.

```xml
<inboundEndpoint xmlns="http://ws.apache.org/ns/synapse" name="HttpsInbound" protocol="https" sequence="foo" suspend="false">
    <parameters>
        <parameter name="inbound.http.port">8443</parameter>
    </parameters>
</inboundEndpoint>
```
