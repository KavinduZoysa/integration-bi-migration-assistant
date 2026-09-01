import ballerina/http;
import ballerina/log;

configurable int mutualKeyInboundPort = 8543;
configurable string mutualKeyInboundHost = "0.0.0.0";
configurable string mutualKeyInboundKeyStorePath = "repository/resources/security/wso2carbon.jks";
configurable string mutualKeyInboundKeyStorePassword = "wso2carbon";

public listener http:Listener mutualKeyInboundListener = new (mutualKeyInboundPort, {host: mutualKeyInboundHost, secureSocket: {key: {path: mutualKeyInboundKeyStorePath, password: mutualKeyInboundKeyStorePassword}}});

service / on mutualKeyInboundListener {
    resource function 'default [string... path](http:Caller caller, http:Request request) returns error? {
        Context ctx = {variables: {}, caller: caller};
        do {
            check emitPayload(ctx, request);
            check foo(ctx);
        } on fail error err {
            log:printError("Unhandled error in mediation", 'error = err);
            ctx.payload = {"error": err.message()};
            ctx.statusCode = 500;
            check respond(ctx);
        }
    }
}
