import ballerina/http;

public listener http:Listener httpListener = new (8080);

service /respond on httpListener {
    resource function get process(http:Caller caller) returns error? {
        Context ctx = {variables: {}, caller: caller};
        ctx.payload = {"msg": "processing"};
        respond2(ctx);
        check respond(ctx);
    }
}
