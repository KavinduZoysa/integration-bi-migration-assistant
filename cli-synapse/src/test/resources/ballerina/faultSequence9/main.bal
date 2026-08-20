import ballerina/data.xmldata;
import ballerina/http;

public listener http:Listener httpListener = new (8080);

service /faultdemo on httpListener {
    resource function post test(http:Caller caller, http:Request request) returns error? {
        Context ctx = {variables: {}, caller: caller};
        do {
            check emitPayload(ctx, request);
            ctx.variables.qtyRaw = convertToString(check xmldata:transform(check convertToXml(ctx.payload), `//quantity`, string));
            ctx.payload = {"Hello": "World"};
            ctx.variables.qty = check convertToInt(ctx.variables.qtyRaw);
            check respond(ctx);
        } on fail error err {
            ctx.variables.ERROR_MESSAGE = err.message();
            ctx.variables.msg = convertToString(ctx.variables.ERROR_MESSAGE);
            ctx.payload = {"status": "error"};
            ctx.statusCode = 500;
            check respond(ctx);
        }
    }
}
