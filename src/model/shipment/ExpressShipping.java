package model.shipment;

import java.math.BigDecimal;

public class ExpressShipping extends Shipment {

	private static final long serialVersionUID = 1L;

	public ExpressShipping() {
    }

    @Override
    public void calculateShippingCharge() {
        this.name = "Express Shipping";
        this.charge = new BigDecimal(5);
    }
    
}
