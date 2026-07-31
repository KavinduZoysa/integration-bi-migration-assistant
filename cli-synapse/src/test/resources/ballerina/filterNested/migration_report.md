# Synapse to Ballerina migration report

1 Synapse construct could not be automatically converted and was left as TODOs in the generated code. Each entry shows the source file and the original Synapse code; review and migrate them manually.

## Unsupported mediator (1)

### `<filter>` — api.xml

Control-flow mediator not supported; the wrapper logic is not applied and nested mediators below need manual restructuring.

```xml
<filter regex="premium" source="$ctx:type" xmlns="http://ws.apache.org/ns/synapse">
                <then>
                    <property name="tier" scope="default" value="premium"/>
                </then>
                <else>
                    <property name="tier" scope="default" value="standard"/>
                </else>
            </filter>
```
