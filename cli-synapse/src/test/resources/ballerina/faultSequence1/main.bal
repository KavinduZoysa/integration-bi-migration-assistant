import ballerina/http;

public listener http:Listener httpListener = new (8080);

service /FaultSequence1 on httpListener {
    resource function get status(http:Caller caller) returns error? {
        Context ctx = {variables: {}, caller: caller};
        do {
            ctx.payload = {"Hello": "World"};
            check respond(ctx);
        } on fail error err {
            ctx.variables.ERROR_MESSAGE = err.message();
            ctx.variables.msg = convertToString(ctx.variables.ERROR_MESSAGE);
            ctx.payload = {"error": "failed"};
            check respond(ctx);
        }
    }
}
