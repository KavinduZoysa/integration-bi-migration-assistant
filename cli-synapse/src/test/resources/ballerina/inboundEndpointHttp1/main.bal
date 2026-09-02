import ballerina/http;

configurable int httpInboundPort = 8085;
configurable string httpInboundHost = "0.0.0.0";

public listener http:Listener httpInboundListener = new (httpInboundPort, {host: httpInboundHost});

service / on httpInboundListener {
    resource function 'default [string... path](http:Caller caller, http:Request request) returns error? {
        Context ctx = {variables: {}, caller: caller};
        do {
            check emitPayload(ctx, request);
            check foo(ctx);
        } on fail error err {
            ctx.variables.ERROR_MESSAGE = err.message();
            check handleError(ctx);
        }
    }
}
