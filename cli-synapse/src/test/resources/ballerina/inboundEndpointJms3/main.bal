import ballerina/log;
import ballerinax/activemq.driver as _;
import ballerinax/java.jms;

configurable string JmsInboundInitialContextFactory = "org.apache.activemq.jndi.ActiveMQInitialContextFactory";
configurable string JmsInboundProviderUrl = "";

public listener jms:Listener JmsInboundListener = new jms:Listener(
    connectionConfig = {initialContextFactory: JmsInboundInitialContextFactory, providerUrl: JmsInboundProviderUrl},
    consumerOptions = {
        destination: {
            'type: jms:QUEUE,
            name: "OrderQueue"
        }
    }
);

service "JmsInbound" on JmsInboundListener {
    remote function onMessage(jms:Message message, jms:Caller caller) returns error? {
        Context ctx = {variables: {}};
        do {
            if message !is jms:TextMessage {
                fail error("Unsupported JMS message type: expected a TextMessage");
            }
            ctx.payload = message.content;
            check foo(ctx);
        } on fail error err {
            log:printError("Unhandled error in mediation", 'error = err);
        }
    }
}
