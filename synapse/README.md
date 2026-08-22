# Synapse to Ballerina migration

Converts WSO2 Synapse (ESB / Micro Integrator) artifacts into a Ballerina package.

> Status: early scaffold. A focused subset of the Synapse REST API surface is supported today
> (see [Supported constructs](#supported-synapse-constructs)) and is being grown incrementally.

## What it does

Given a Synapse REST API definition, the tool generates a Ballerina package — a `main.bal`
containing the equivalent HTTP service plus a `Ballerina.toml` manifest. Each `<api>` becomes an
HTTP service, each `<resource>` becomes a resource function, and the mediators inside a resource
are translated into the function body.

## Building the project

Prerequisite: JDK 21.

Build the runnable migration jar using the Gradle wrapper from the repository root:

```sh
./gradlew :cli-synapse:synapseJar
```

This produces `cli-synapse/build/libs/synapse-migration-assistant-<version>.jar`.

## Running the migration tool

Once the jar is built, run the migration tool with the following command:

```sh
java -jar cli-synapse/build/libs/synapse-migration-assistant-<version>.jar <synapse-artifact-file> [-o|--out <output-directory>]
```

**Parameters:**
- `<synapse-artifact-file>`: Path to the Synapse artifact file to be converted.
- `-o`, `--out` `<output-directory>`: Optional. Directory to write the generated Ballerina package into.

**Output:**
- A Ballerina package (a `main.bal` and a `Ballerina.toml`) is generated. By default it is written to a directory named after the input file with a `_converted` suffix; if `-o`/`--out` is given, it is written to that directory instead.

## Supported Synapse constructs

The migration tool currently supports the following Synapse elements:

### Artifacts

| Tag | Converted to |
|-----|--------------|
| `<api>` | HTTP service |
| `<resource>` | resource function |
| `<inSequence>` | resource function body |
| `<inboundEndpoint>` (`protocol="http"`) | dedicated `http:Listener` (port from the `inbound.http.port` parameter) plus a wildcard service forwarding every request to the referenced `sequence` |

### Mediators

| Tag | Converted to |
|-----|--------------|
| `<payloadFactory>` | response payload |
| `<respond>` | response return |
| `<property>` (static name only) | response header, status code, or local variable |
| `<faultSequence>` (inline or resolved `key="…"` reference to a project-level sequence) | `on fail` clause of a `do` block wrapping the resource body |

## Unsupported constructs (TODOs)

The migration never aborts on an unsupported construct. Instead, every construct with no Ballerina
translation is surfaced as a TODO so the generated package still builds around the supported parts:

- **Unsupported mediators** (e.g. `<log>`, `<filter>`, `<switch>`, `<call>`) become a `// TODO` comment
  in the generated function body, carrying the original Synapse XML and its source file. For a
  control-flow wrapper (`<filter>`, `<switch>`, `<foreach>`, `<iterate>`, `<aggregate>`, `<clone>`), the supported
  mediators nested in its branches are still converted best-effort (the wrapper's control flow is not
  applied — the TODO flags that it needs manual restructuring).
- **Unsupported top-level artifacts** (e.g. `<proxy>`, `<endpoint>`) are reported in
  `migration_report.md` (they have no Ballerina construct to host an inline comment).
- **`<inboundEndpoint>` protocols other than `http`/`https`** (e.g. `jms`, `file`, `ws`, a `class=…`
  custom Java endpoint) have no generated listener equivalent yet and are reported in
  `migration_report.md` the same way an unsupported top-level artifact is. An `inboundEndpoint`
  parameter other than `inbound.http.port`/`inbound.http.host` is likewise reported rather than
  silently ignored.
- **Unsupported `<property>` scopes / `remove` actions** and **unresolved `<sequence key="…"/>`
  references** become inline `// TODO` comments and are recorded in the report.

Every unsupported case is also aggregated into a `migration_report.md` at the package root (source file
+ original Synapse code per entry). The report is written only when there is at least one unsupported
case; under `--dry-run` it is printed instead of written.

## Example

Input (`HelloWorldService/helloWorld.xml`):

```xml
<api context="/HelloWorld" name="HelloWorld" xmlns="http://ws.apache.org/ns/synapse">
    <resource methods="GET" uri-template="/status/{id}">
        <inSequence>
            <payloadFactory media-type="json">
                <format>{"Hello":"World"}</format>
            </payloadFactory>
            <respond/>
        </inSequence>
    </resource>
</api>
```

Output (`main.bal`):

```ballerina
import ballerina/http;

public listener http:Listener httpListener = new (8080);

service /HelloWorld on httpListener {
    resource function get status/[string id]() returns http:Response {
        http:Response response = new;
        response.setPayload({"Hello": "World"});
        return response;
    }
}
```

## Sample conversion projects

Sample Synapse artifacts live under `cli-synapse/src/test/resources/synapse/<Name>`, paired by name
with the expected Ballerina packages under `cli-synapse/src/test/resources/ballerina/<Name>`. To add
a case, drop `synapse/<Name>/<Name>.xml` and the expected `ballerina/<Name>` package.

## Known limitations

- `<proxy>` services, `<log>`, `<filter>` and other mediators/artifacts are not converted, but they no
  longer fail the migration: they are surfaced as TODOs (see [Unsupported constructs](#unsupported-constructs-todos)).
- `<outSequence>` (the out flow) is not yet migrated. `<faultSequence>` (the error flow) is now
  supported — an unresolved `key="…"` reference or a resource with no fault sequence at all falls back
  to the project-level default and is reported if unresolved.
- The response payload is set with a generic setter rather than media-type-specific ones (e.g. JSON/text/XML setters).
- The shared HTTP listener every `<api>` service binds to is fixed (port `8080`) and is not derived
  from the source artifact. An `<inboundEndpoint>` is the exception: it gets its own dedicated
  `http:Listener`, with the port read from its `inbound.http.port` parameter.
