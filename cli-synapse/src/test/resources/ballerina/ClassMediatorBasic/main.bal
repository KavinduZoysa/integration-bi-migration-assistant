import ballerina/http;

public listener http:Listener httpListener = new (8080);

service /hello on httpListener {
    resource function get greet(http:Caller caller) returns error? {
        Context ctx = {variables: {}, caller: caller};
        ctx.payload = {"msg": "hello"};
        ctx.variables.who = "world";
        greetMediator(ctx, "en", convertToString(ctx.variables.who));
        check respond(ctx);
    }
}
