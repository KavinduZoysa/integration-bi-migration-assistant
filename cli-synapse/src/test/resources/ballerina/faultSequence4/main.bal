import ballerina/http;

public listener http:Listener httpListener = new (8080);

service /NamedFaultSequence1 on httpListener {
    resource function get status(http:Caller caller) returns error? {
        Context ctx = {variables: {}, caller: caller};
        do {
            ctx.payload = {"Hello": "World"};
            check respond(ctx);
        } on fail error err {
            ctx.variables.ERROR_MESSAGE = err.message();
            check myFault(ctx);
        }
    }
}
