import ballerina/http;
import ballerina/log;

configurable int PlainHttpInboundPort = 8085;
configurable string PlainHttpInboundHost = "0.0.0.0";

public listener http:Listener PlainHttpInboundListener = new (PlainHttpInboundPort, {host: PlainHttpInboundHost});

service / on PlainHttpInboundListener {
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
