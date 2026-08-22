import ballerina/http;

public listener http:Listener httpListener = new (8080);

service /NamedFaultSequenceUnresolvedWithProjectFault1 on httpListener {
    resource function get status(http:Caller caller) returns error? {
        Context ctx = {variables: {}, caller: caller};
        // TODO: Unresolved Synapse fault sequence reference 'missingFault' (from api.xml). Referenced fault sequence
        // 'missingFault' was not found among the converted artifacts; falling back to the project-level 'fault'
        // sequence.
        do {
            ctx.payload = {"Hello": "World"};
            check respond(ctx);
        } on fail error err {
            ctx.variables.ERROR_MESSAGE = err.message();
            check fault(ctx);
        }
    }
}
