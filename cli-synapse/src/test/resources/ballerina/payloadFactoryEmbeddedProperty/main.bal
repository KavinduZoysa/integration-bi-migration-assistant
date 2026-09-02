import ballerina/http;
import ballerina/log;

public listener http:Listener httpListener = new (8080);

service /PayloadFactoryEmbeddedProperty on httpListener {
    resource function get status(http:Caller caller) returns error? {
        Context ctx = {variables: {}, caller: caller};
        do {
            ctx.payload = {"Hello": "World"};
            check respond(ctx);
        } on fail error err {
            ctx.variables.ERROR_MESSAGE = err.message();
            ctx.payload = {"error": `Request failed: ${ctx.variables.ERROR_MESSAGE} - please retry`};
            check respond(ctx);
        }
    }

    resource function get unknown(http:Caller caller) returns error? {
        Context ctx = {variables: {}, caller: caller};
        do {
            // TODO: This payloadFactory format references '${properties...}' placeholder(s) for NOT_A_REAL_PROP, not among the known default-scope properties; the literal template text is left in the generated payload. Manual conversion required.
            ctx.payload = {"note": "Value: ${properties.default.NOT_A_REAL_PROP} end"};
            check respond(ctx);
        } on fail error err {
            log:printError("Unhandled error in mediation", 'error = err);
            ctx.payload = {"error": err.message()};
            ctx.statusCode = 500;
            check respond(ctx);
        }
    }
}
