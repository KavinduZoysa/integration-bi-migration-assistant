# Synapse to Ballerina migration report

1 Synapse construct could not be automatically converted and was left as TODOs in the generated code. Each entry shows the source file and the original Synapse code; review and migrate them manually.

## Unmodeled inbound endpoint keystore key password (1)

### `<parameter>` — inboundEndpoint.xml

Inbound endpoint 'MutualKeyInbound' keystore declares a KeyPassword distinct from its own Password; crypto:KeyStore has no separate key-password field, so the keystore password is used for both. Manual verification required.

```xml
<inboundEndpoint xmlns="http://ws.apache.org/ns/synapse" name="MutualKeyInbound" protocol="https" sequence="foo" suspend="false">
    <parameters>
        <parameter name="inbound.http.port">8543</parameter>
        <parameter name="keystore">
            <KeyStore>
                <Location>repository/resources/security/wso2carbon.jks</Location>
                <Type>JKS</Type>
                <Password>wso2carbon</Password>
                <KeyPassword>differentKeyPassword</KeyPassword>
            </KeyStore>
        </parameter>
    </parameters>
</inboundEndpoint>
```
