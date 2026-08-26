# Synapse to Ballerina migration report

1 Synapse construct could not be automatically converted and was left as TODOs in the generated code. Each entry shows the source file and the original Synapse code; review and migrate them manually.

## Unsupported payloadFactory property template (1)

### `<payloadFactory>` — api.xml

This payloadFactory format references '${properties...}' placeholder(s) for NOT_A_REAL_PROP, not among the known default-scope properties; the literal template text is left in the generated payload. Manual conversion required.

```xml
{"note": "Value: ${properties.default.NOT_A_REAL_PROP} end"}
```
