import ballerina/http;

configurable int HttpInboundPort = 8085;
configurable string HttpInboundHost = "0.0.0.0";

public listener http:Listener HttpInboundListener = new (HttpInboundPort, {host: HttpInboundHost});

service / on HttpInboundListener {
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
