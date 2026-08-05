import ballerina/http;

public listener http:Listener httpListener = new (8080);

service /order  on httpListener {
    resource function get process(http:Caller caller) returns error? {
        Context ctx = {variables: {}, caller: caller};
        ctx.payload = {"msg": "processing"};
        orderMediator(ctx);
        check respond(ctx);
    }
}
