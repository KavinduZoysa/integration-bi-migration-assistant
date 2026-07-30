# Synapse to Ballerina migration report

1 Synapse construct could not be automatically converted and was left as TODOs in the generated code. Each entry shows the source file and the original Synapse code; review and migrate them manually.

## Unsupported mediator (1)

### `<log>` — HealthCheckApi.xml

Mediator not supported; manual conversion required.

```xml
<log level="custom" xmlns="http://ws.apache.org/ns/synapse">
                <property name="message" value="Health check requested"/>
            </log>
```
