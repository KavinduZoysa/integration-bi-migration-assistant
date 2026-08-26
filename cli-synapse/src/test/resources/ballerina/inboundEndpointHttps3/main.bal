import ballerina/http;
import ballerina/log;

configurable int MutualKeyInboundPort = 8543;
configurable string MutualKeyInboundHost = "0.0.0.0";
configurable string MutualKeyInboundKeyStorePath = "repository/resources/security/wso2carbon.jks";
configurable string MutualKeyInboundKeyStorePassword = "wso2carbon";

public listener http:Listener MutualKeyInboundListener = new (MutualKeyInboundPort, {host: MutualKeyInboundHost, secureSocket: {key: {path: MutualKeyInboundKeyStorePath, password: MutualKeyInboundKeyStorePassword}}});

service / on MutualKeyInboundListener {
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
