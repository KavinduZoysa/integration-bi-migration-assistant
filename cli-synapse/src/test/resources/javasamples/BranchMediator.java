package javasamples;

import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

public class BranchMediator extends AbstractMediator {

    public boolean mediate(MessageContext mc) {
        String lang = (String) mc.getProperty("lang");
        if ("en".equals(lang)) {
            mc.setProperty("greeting", "Hello");
        } else if ("fr".equals(lang)) {
            mc.setProperty("greeting", "Bonjour");
        } else {
            mc.setProperty("greeting", "Hi");
        }
        return true;
    }
}
