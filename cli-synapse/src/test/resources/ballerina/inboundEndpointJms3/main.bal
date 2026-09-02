import ballerina/log;
import ballerinax/activemq.driver as _;
import ballerinax/java.jms;

configurable string jmsInboundInitialContextFactory = "org.apache.activemq.jndi.ActiveMQInitialContextFactory";
configurable string jmsInboundProviderUrl = "";

public listener jms:Listener jmsInboundListener = new jms:Listener(
    connectionConfig = {initialContextFactory: jmsInboundInitialContextFactory, providerUrl: jmsInboundProviderUrl},
    consumerOptions = {
        destination: {
            'type: jms:QUEUE,
            name: "OrderQueue"
        }
    }
);

service "JmsInbound" on jmsInboundListener {
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
