import ballerina/http;

public listener http:Listener httpListener = new (8080);

public listener http:Listener HttpInboundEPListener = new (8085);

service / on HttpInboundEPListener {
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
