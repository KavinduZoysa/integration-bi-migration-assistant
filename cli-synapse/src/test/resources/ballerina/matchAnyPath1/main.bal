import ballerina/http;

public listener http:Listener httpListener = new (8080);

service /gateway on httpListener {
    resource function 'default [string... path](http:Caller caller, http:Request request) returns error? {
        Context ctx = {variables: {}, caller: caller};
        check emitPayload(ctx, request);
        ctx.payload = {"matched": true};
        check respond(ctx);
    }
}
