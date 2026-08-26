import ballerina/log;
import ballerinax/activemq.driver as _;
import ballerinax/java.jms;

configurable string OrderInboundInitialContextFactory = "org.apache.activemq.jndi.ActiveMQInitialContextFactory";
configurable string OrderInboundProviderUrl = "tcp://localhost:61616";
configurable string OrderInboundUsername = "admin";
configurable string OrderInboundPassword = "admin";

public listener jms:Listener OrderInboundListener = new jms:Listener(
    connectionConfig = {initialContextFactory: OrderInboundInitialContextFactory, providerUrl: OrderInboundProviderUrl, username: OrderInboundUsername, password: OrderInboundPassword},
    consumerOptions = {
        destination: {
            'type: jms:QUEUE,
            name: "OrderQueue"
        }
    }
);

service "OrderInbound" on OrderInboundListener {
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
