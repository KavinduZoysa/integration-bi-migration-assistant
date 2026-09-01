import ballerina/log;
import ballerinax/activemq.driver as _;
import ballerinax/java.jms;

configurable string orderInboundInitialContextFactory = "org.apache.activemq.jndi.ActiveMQInitialContextFactory";
configurable string orderInboundProviderUrl = "tcp://localhost:61616";
configurable string orderInboundUsername = "admin";
configurable string orderInboundPassword = "admin";

public listener jms:Listener orderInboundListener = new jms:Listener(
    connectionConfig = {initialContextFactory: orderInboundInitialContextFactory, providerUrl: orderInboundProviderUrl, username: orderInboundUsername, password: orderInboundPassword},
    consumerOptions = {
        destination: {
            'type: jms:QUEUE,
            name: "OrderQueue"
        }
    }
);

service "OrderInbound" on orderInboundListener {
    remote function onMessage(jms:Message message, jms:Caller caller) returns error? {
        Context ctx = {variables: {}};
        do {
            if message !is jms:TextMessage {
                fail error("Unsupported JMS message type: expected a TextMessage");
            }
            ctx.payload = message.content;
            check processOrder(ctx);
        } on fail error err {
            log:printError("Unhandled error in mediation", 'error = err);
        }
    }
}
