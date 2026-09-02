import ballerina/http;

configurable int httpInboundEPPort = 8085;
configurable string httpInboundEPHost = "0.0.0.0";

public listener http:Listener httpInboundEPListener = new (httpInboundEPPort, {host: httpInboundEPHost});

service / on httpInboundEPListener {
    resource function 'default [string... path](http:Caller caller, http:Request request) returns error? {
        Context ctx = {variables: {}, caller: caller};
        do {
            check emitPayload(ctx, request);
            check HttpInboundSeq(ctx);
        } on fail error err {
            ctx.variables.ERROR_MESSAGE = err.message();
            check HttpInboundFaultSeq(ctx);
        }
    }
}
