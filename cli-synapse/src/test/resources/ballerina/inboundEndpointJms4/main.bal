import ballerinax/activemq.driver as _;
import ballerinax/java.jms;

configurable string JMSInboundEndpointInitialContextFactory = "org.apache.activemq.jndi.ActiveMQInitialContextFactory";
configurable string JMSInboundEndpointProviderUrl = "tcp://localhost:61616";

public listener jms:Listener JMSInboundEndpointListener = new jms:Listener(
    connectionConfig = {initialContextFactory: JMSInboundEndpointInitialContextFactory, providerUrl: JMSInboundEndpointProviderUrl},
    consumerOptions = {
        destination: {
            'type: jms:QUEUE,
            name: "TestQueue"
        }
    }
);

service "JMSInboundEndpoint" on JMSInboundEndpointListener {
    remote function onMessage(jms:Message message, jms:Caller caller) returns error? {
        Context ctx = {variables: {}};
        do {
            if message !is jms:TextMessage {
                fail error("Unsupported JMS message type: expected a TextMessage");
            }
            ctx.payload = message.content;
            check JMSInjectingSeq();
        } on fail error err {
            ctx.variables.ERROR_MESSAGE = err.message();
            check JMSErrorSeq();
        }
    }
}
