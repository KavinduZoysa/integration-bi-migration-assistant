import ballerina/http;
import ballerina/log;

configurable int SecureInboundEndpointPort = 8343;
configurable string SecureInboundEndpointHost = "0.0.0.0";
configurable string SecureInboundEndpointKeyStorePath = "repository/resources/security/wso2carbon.jks";
configurable string SecureInboundEndpointKeyStorePassword = "wso2carbon";

public listener http:Listener SecureInboundEndpointListener = new (SecureInboundEndpointPort, {host: SecureInboundEndpointHost, secureSocket: {key: {path: SecureInboundEndpointKeyStorePath, password: SecureInboundEndpointKeyStorePassword}}});

service / on SecureInboundEndpointListener {
    resource function 'default [string... path](http:Caller caller, http:Request request) returns error? {
        Context ctx = {variables: {}, caller: caller};
        // TODO: Unresolved Synapse fault sequence reference 'fault' (from inboundEndpoint.xml). Referenced fault sequence 'fault' was not found among the converted artifacts; falling back to the default error handler.
        do {
            check emitPayload(ctx, request);
            // TODO: Unresolved Synapse sequence reference 'FileProcessSequence' (from inboundEndpoint.xml). Referenced sequence 'FileProcessSequence' was not found among the converted artifacts; manual conversion required.
            // Original Synapse:
            // <sequence key="FileProcessSequence"/>
            fail error("Unresolved sequence reference: 'FileProcessSequence'");
        } on fail error err {
            log:printError("Unhandled error in mediation", 'error = err);
            ctx.payload = {"error": err.message()};
            ctx.statusCode = 500;
            check respond(ctx);
        }
    }
}
