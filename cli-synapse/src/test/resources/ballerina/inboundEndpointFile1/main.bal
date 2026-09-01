import ballerina/file;
import ballerina/log;

configurable string fileInboundPath = "/data/in";

public listener file:Listener fileInboundListener = new (
    path = fileInboundPath,
    recursive = false
);

// Synapse VFS inbound endpoints process each discovered file exactly once; there is no onModify equivalent.
service on fileInboundListener {
    remote function onCreate(file:FileEvent event) returns error? {
        Context ctx = {variables: {}};
        do {
            check processFile(ctx);
        } on fail error err {
            log:printError("Unhandled error in mediation", 'error = err);
        }
    }
}
