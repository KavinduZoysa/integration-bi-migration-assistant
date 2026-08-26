import ballerina/file;

configurable string FileInboundEndpointPath = "C:/projects/Test/inbound/input";

public listener file:Listener FileInboundEndpointListener = new (
    path = FileInboundEndpointPath,
    recursive = false
);

// Synapse VFS inbound endpoints process each discovered file exactly once; there is no onModify equivalent.
service on FileInboundEndpointListener {
    remote function onCreate(file:FileEvent event) returns error? {
        Context ctx = {variables: {}};
        do {
            check FileProcessSequence();
        } on fail error err {
            ctx.variables.ERROR_MESSAGE = err.message();
            check FileErrorSequence();
        }
    }
}
