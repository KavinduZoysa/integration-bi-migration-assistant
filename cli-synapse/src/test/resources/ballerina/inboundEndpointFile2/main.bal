import ballerina/file;
import ballerina/log;

configurable string FileArchiveInboundPath = "/data/in";

public listener file:Listener FileArchiveInboundListener = new (
    path = FileArchiveInboundPath,
    recursive = false
);

// Synapse VFS inbound endpoints process each discovered file exactly once; there is no onModify equivalent.
service on FileArchiveInboundListener {
    remote function onCreate(file:FileEvent event) returns error? {
        Context ctx = {variables: {}};
        do {
            check processArchive(ctx);
        } on fail error err {
            log:printError("Unhandled error in mediation", 'error = err);
        }
    }
}
