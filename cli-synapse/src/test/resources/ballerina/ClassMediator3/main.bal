import ballerina/http;

public listener http:Listener httpListener = new (8080);

service /branch on httpListener {
    resource function get greet(http:Caller caller) returns error? {
        Context ctx = {variables: {}, caller: caller};
        ctx.payload = {"msg": "hello"};
        branchMediator(ctx);
        check respond(ctx);
    }
}
