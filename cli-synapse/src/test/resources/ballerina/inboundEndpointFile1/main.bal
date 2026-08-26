import ballerina/file;
import ballerina/log;

configurable string FileInboundPath = "/data/in";

public listener file:Listener FileInboundListener = new (
    path = FileInboundPath,
    recursive = false
);

// Synapse VFS inbound endpoints process each discovered file exactly once; there is no onModify equivalent.
service on FileInboundListener {
    remote function onCreate(file:FileEvent event) returns error? {
        Context ctx = {variables: {}};
        do {
            check processFile(ctx);
        } on fail error err {
            log:printError("Unhandled error in mediation", 'error = err);
        }
    }
}
