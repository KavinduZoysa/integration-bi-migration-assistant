import ballerina/http;

public listener http:Listener httpListener = new (8080);

service /DefaultFaultSequence1 on httpListener {
    resource function get status(http:Caller caller) returns error? {
        Context ctx = {variables: {}, caller: caller};
        // TODO: Implicit Synapse fault sequence 'fault'. This resource has no faultSequence of its own; because a
        // project-level sequence named 'fault' exists, it is used implicitly as this resource's error handler. Verify
        // this matches the intended behavior, or rename the sequence if it is unrelated to error handling.
        do {
            ctx.payload = {"Hello": "World"};
            check respond(ctx);
        } on fail error err {
            ctx.variables.ERROR_MESSAGE = err.message();
            check fault(ctx);
        }
    }
}
