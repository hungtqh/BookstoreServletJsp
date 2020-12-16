package model.shipment;

import java.math.BigDecimal;

public class StandardShipping extends Shipment {

	private static final long serialVersionUID = 1L;

	public StandardShipping() {
    }

    @Override
    public void calculateShippingCharge() {
        this.name = "Standard Shipping";
        this.charge = new BigDecimal(2);
    }
    
}
