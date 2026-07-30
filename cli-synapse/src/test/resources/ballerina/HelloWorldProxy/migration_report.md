# Synapse to Ballerina migration report

1 Synapse construct could not be automatically converted and was left as TODOs in the generated code. Each entry shows the source file and the original Synapse code; review and migrate them manually.

## Unsupported artifact (1)

### `<proxy>` — HelloWorldProxy.xml

Top-level '<proxy>' artifact is not supported; manual conversion required.

```xml
<proxy xmlns="http://ws.apache.org/ns/synapse" name="HelloWorldProxy" startOnLoad="true" transports="http https">
    <target>
        <inSequence>
            <log level="full">
                <property name="message" value="HelloWorldProxy invoked"/>
            </log>
            <payloadFactory media-type="json">
                <format>{"message":"Hello, World!"}</format>
                <args/>
            </payloadFactory>
            <respond/>
        </inSequence>
        <outSequence/>
        <faultSequence/>
    </target>
</proxy>
```
