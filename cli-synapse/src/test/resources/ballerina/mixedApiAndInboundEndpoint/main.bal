import ballerina/log;
import ballerina/http;

public listener http:Listener httpListener = new (8080);

service /mixed on httpListener {
    resource function get status(http:Caller caller) returns error? {
        Context ctx = {variables: {}, caller: caller};
        do {
            ctx.payload = {"status": "UP"};
            check respond(ctx);
        } on fail error err {
        }
    }
}

configurable int MixedInboundPort = 8089;
configurable string MixedInboundHost = "0.0.0.0";

public listener http:Listener MixedInboundListener = new (MixedInboundPort, {host: MixedInboundHost});

service / on MixedInboundListener {
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
