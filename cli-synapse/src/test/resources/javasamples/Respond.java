package javasamples;

import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

public class Respond extends AbstractMediator {

    public boolean mediate(MessageContext mc) {
        mc.setProperty("handled", "true");
        return true;
    }
}
