import ballerina/log;
import ballerinax/java.jms;

configurable string topicInboundInitialContextFactory = "";
configurable string topicInboundProviderUrl = "";

public listener jms:Listener topicInboundListener = new jms:Listener(
    connectionConfig = {initialContextFactory: topicInboundInitialContextFactory, providerUrl: topicInboundProviderUrl},
    consumerOptions = {
        destination: {
            'type: jms:QUEUE,
            name: "NotificationTopic"
        }
    }
);

service "TopicInbound" on topicInboundListener {
    remote function onMessage(jms:Message message, jms:Caller caller) returns error? {
        Context ctx = {variables: {}};
        do {
            if message !is jms:TextMessage {
                fail error("Unsupported JMS message type: expected a TextMessage");
            }
            ctx.payload = message.content;
            check processTopic(ctx);
        } on fail error err {
            log:printError("Unhandled error in mediation", 'error = err);
        }
    }
}
