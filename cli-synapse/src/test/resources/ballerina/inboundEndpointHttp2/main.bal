import ballerina/http;
import ballerina/log;

configurable int NoErrorInboundPort = 8086;
configurable string NoErrorInboundHost = "0.0.0.0";

public listener http:Listener NoErrorInboundListener = new (NoErrorInboundPort, {host: NoErrorInboundHost});

service / on NoErrorInboundListener {
    resource function 'default [string... path](http:Caller caller, http:Request request) returns error? {
        Context ctx = {variables: {}, caller: caller};
        do {
            check emitPayload(ctx, request);
            check foo(ctx);
        } on fail error err {
            log:printError("Unhandled error in mediation", 'error = err);
            ctx.payload = {"error": err.message()};
            ctx.statusCode = 500;
            check respond(ctx);
        }
    }
}
