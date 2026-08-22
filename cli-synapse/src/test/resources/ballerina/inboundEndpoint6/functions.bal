import ballerina/http;

function HttpInboundFaultSeq(Context ctx) returns error? {
    // TODO: Unsupported Synapse mediator '<log>' (from HttpInboundFaultSeq.xml). Mediator not supported; manual
    // conversion required.
    // Original Synapse:
    // <log category="ERROR" xmlns="http://ws.apache.org/ns/synapse">
    //         <message>Inbound error: ${properties.synapse.ERROR_MESSAGE}</message>
    //     </log>
    ctx.payload = {"status": "error", "message": ctx.variables.ERROR_MESSAGE};
    ctx.statusCode = 500;
    check respond(ctx);
}

function HttpInboundSeq(Context ctx) returns error? {
    // TODO: Unsupported Synapse mediator '<log>' (from HttpInboundSeq.xml). Mediator not supported; manual
    // conversion required.
    // Original Synapse:
    // <log category="INFO" logFullPayload="true" xmlns="http://ws.apache.org/ns/synapse">
    //         <message>Inbound request received</message>
    //     </log>
    ctx.payload = {"status": "received", "message": "Hello from HTTP Inbound Endpoint"};
    ctx.statusCode = 200;
    check respond(ctx);
}

function respond(Context ctx) returns error? {
    http:Response response = new;
    response.setPayload(ctx.payload);
    foreach [string, string] [name, value] in ctx.headers.entries() {
        response.setHeader(name, value);
    }
    int? statusCode = ctx.statusCode;
    if statusCode is int {
        response.statusCode = statusCode;
    }
    check (<http:Caller>ctx.caller)->respond(response);
}

function emitPayload(Context ctx, http:Request request) returns error? {
    string contentType = request.getContentType();
    if contentType.startsWith("application/json") {
        ctx.payload = check request.getJsonPayload();
    } else if contentType.startsWith("application/xml") || contentType.startsWith("text/xml") {
        ctx.payload = check request.getXmlPayload();
    } else if contentType.startsWith("text/") {
        ctx.payload = check request.getTextPayload();
    } else {
        ctx.payload = check request.getBinaryPayload();
    }
}
