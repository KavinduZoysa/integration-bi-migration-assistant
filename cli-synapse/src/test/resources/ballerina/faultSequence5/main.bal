import ballerina/log;

import ballerina/http;

public listener http:Listener httpListener = new (8080);

service /NamedFaultSequenceUnresolved1 on httpListener {
    resource function get status(http:Caller caller) returns error? {
        Context ctx = {variables: {}, caller: caller};
        // TODO: Unresolved Synapse fault sequence reference 'missingFault' (from faultSequence5.xml). Referenced fault sequence 'missingFault' was not found among the converted artifacts; falling back to the default error handler.
        do {
            ctx.payload = {"Hello": "World"};
            check helper(ctx);
            check respond(ctx);
        } on fail error err {
            log:printError("Unhandled error in mediation", 'error = err);
            ctx.payload = {"error": err.message()};
            ctx.statusCode = 500;
            check respond(ctx);
        }
    }
}
