import ballerina/http;
import ballerina/log;

public type Vars record {|
    string httpStatus?;
|};

public type Attributes record {|
    http:Request request?;
    http:Response response?;
    map<string> uriParams = {};
|};

public type Context record {|
    anydata payload = ();
    Vars vars = {};
    Attributes attributes;
|};

public listener http:Listener listener\-config = new (8081);

service http:InterceptableService / on listener\-config {
    function init() returns error? {
    }

    public function createInterceptors() returns [MuleResponseErrorInterceptor0, MuleResponseInterceptor0] {
        return [new MuleResponseErrorInterceptor0(), new MuleResponseInterceptor0()];
    }

    resource function default [string... path](http:Request request) returns http:Response|error {
        return error("APIKIT:NOT_FOUND");
    }

    resource function get orders/[string id](http:Request request) returns http:Response|error {
        Context ctx = {attributes: {request, response: new, uriParams: {id}}};
        log:printInfo("Get order");

        (<http:Response>ctx.attributes.response).setPayload(ctx.payload);
        return <http:Response>ctx.attributes.response;
    }

    resource function post orders(http:Request request) returns http:Response|error {
        Context ctx = {attributes: {request, response: new}};
        log:printInfo("Create order");

        (<http:Response>ctx.attributes.response).setPayload(ctx.payload);
        return <http:Response>ctx.attributes.response;
    }
}

service class MuleResponseErrorInterceptor0 {
    *http:ResponseErrorInterceptor;

    remote function interceptResponseError(http:RequestContext requestContext, http:Response interceptedResponse, error err) returns http:Response|error {
        Context ctx = {attributes: {response: interceptedResponse}};
        // TODO: if conditions may require some manual adjustments
        if err is "APIKIT:BAD_REQUEST" {

            // on-error-propagate

            ctx.vars.httpStatus = "400";

            // set payload

            string payload6 = "{\"message\":\"Bad request\"}";
            ctx.payload = payload6;
            http:Response response = <http:Response>ctx.attributes.response;
            response.statusCode = 500;
        } else if err is "APIKIT:NOT_FOUND" {
            // on-error-continue
            ctx.vars.httpStatus = "404";

            // set payload
            string payload7 = "{\"message\":\"Resource not found\"}";
            ctx.payload = payload7;
        } else if err is "APIKIT:METHOD_NOT_ALLOWED" {
            // on-error-propagate
            ctx.vars.httpStatus = "405";

            // set payload
            string payload8 = "{\"message\":\"Method not allowed\"}";
            ctx.payload = payload8;
            http:Response response = <http:Response>ctx.attributes.response;
            response.statusCode = 500;
        } else if err is "APIKIT:NOT_ACCEPTABLE" {
            // on-error-propagate
            ctx.vars.httpStatus = "406";

            // set payload
            string payload9 = "{\"message\":\"Not acceptable\"}";
            ctx.payload = payload9;
            http:Response response = <http:Response>ctx.attributes.response;
            response.statusCode = 500;
        } else if err is "APIKIT:UNSUPPORTED_MEDIA_TYPE" {
            // on-error-propagate
            ctx.vars.httpStatus = "415";

            // set payload
            string payload10 = "{\"message\":\"Unsupported media type\"}";
            ctx.payload = payload10;
            http:Response response = <http:Response>ctx.attributes.response;
            response.statusCode = 500;
        } else if err is "APIKIT:NOT_IMPLEMENTED" {
            // on-error-propagate
            ctx.vars.httpStatus = "501";

            // set payload
            string payload11 = "{\"message\":\"Not implemented\"}";
            ctx.payload = payload11;
            http:Response response = <http:Response>ctx.attributes.response;
            response.statusCode = 500;
        }
        (<http:Response>ctx.attributes.response).setPayload(ctx.payload);
        return <http:Response>ctx.attributes.response;
    }
}

service class MuleResponseInterceptor0 {
    *http:ResponseInterceptor;

    remote function interceptResponse(http:RequestContext requestContext, http:Response response) returns http:Response|error {
        Context ctx = {attributes: {response: response}};
        (<http:Response>ctx.attributes.response).setPayload(ctx.payload);
        return response;
    }
}
