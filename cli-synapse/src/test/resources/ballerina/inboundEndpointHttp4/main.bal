import ballerina/http;

configurable int implicitFaultInboundPort = 8088;
configurable string implicitFaultInboundHost = "0.0.0.0";

public listener http:Listener implicitFaultInboundListener = new (implicitFaultInboundPort, {host: implicitFaultInboundHost});

service / on implicitFaultInboundListener {
    resource function 'default [string... path](http:Caller caller, http:Request request) returns error? {
        Context ctx = {variables: {}, caller: caller};
        // TODO: Implicit Synapse fault sequence 'fault'. This inbound endpoint has no onError of its own; because a project-level sequence named 'fault' exists, it is used implicitly as this inbound endpoint's error handler. Verify this matches the intended behavior, or rename the sequence if it is unrelated to error handling.
        do {
            check emitPayload(ctx, request);
            check foo(ctx);
        } on fail error err {
            ctx.variables.ERROR_MESSAGE = err.message();
            check fault(ctx);
        }
    }
}
