# Synapse to Ballerina migration report

1 Synapse construct could not be automatically converted and was left as TODOs in the generated code. Each entry shows the source file and the original Synapse code; review and migrate them manually.

## Implicit fault sequence (1)

### `<onError>` — inboundEndpoint.xml

This inbound endpoint has no onError of its own; because a project-level sequence named 'fault' exists, it is used implicitly as this inbound endpoint's error handler. Verify this matches the intended behavior, or rename the sequence if it is unrelated to error handling.

```xml

```
