import ballerina/http;
import ballerina/log;

configurable int unresolvedInboundPort = 8087;
configurable string unresolvedInboundHost = "0.0.0.0";

public listener http:Listener unresolvedInboundListener = new (unresolvedInboundPort, {host: unresolvedInboundHost});

service / on unresolvedInboundListener {
    resource function 'default [string... path](http:Caller caller, http:Request request) returns error? {
        Context ctx = {variables: {}, caller: caller};
        do {
            check emitPayload(ctx, request);
            // TODO: Unresolved Synapse sequence reference 'missing' (from inboundEndpoint.xml). Referenced sequence 'missing' was not found among the converted artifacts; manual conversion required.
            // Original Synapse:
            // <sequence key="missing"/>
            fail error("Unresolved sequence reference: 'missing'");
        } on fail error err {
            log:printError("Unhandled error in mediation", 'error = err);
            ctx.payload = {"error": err.message()};
            ctx.statusCode = 500;
            check respond(ctx);
        }
    }
}
