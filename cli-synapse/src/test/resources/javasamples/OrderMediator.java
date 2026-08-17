package javasamples;

import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

public class OrderMediator extends AbstractMediator {

    private float computeDiscount(MessageContext mc) {
        return 0.1f;
    }

    private void applySurcharge(MessageContext mc) {
        mc.setProperty("surcharge", 5.0);
    }

    public boolean mediate(MessageContext mc) {
        int quantity = (int) mc.getProperty("quantity");
        boolean premium = (boolean) mc.getProperty("premium");
        mc.setProperty("discount", computeDiscount(mc));
        mc.setProperty("summary", generateSummary());
        applySurcharge(mc);
        return true;
    }
}
