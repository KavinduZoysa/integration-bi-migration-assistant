# Synapse to Ballerina migration report

1 Synapse construct could not be automatically converted and was left as TODOs in the generated code. Each entry shows the source file and the original Synapse code; review and migrate them manually.

## Implicit fault sequence (1)

### `<faultSequence>` — inboundEndpoint.xml

This resource has no faultSequence of its own; because a project-level sequence named 'fault' exists, it is used implicitly as this resource's error handler. Verify this matches the intended behavior, or rename the sequence if it is unrelated to error handling.

```xml

```
