package model.shipment;

public class ShipmentFactory {
    public static Shipment createShipment(String shippingMethod) {
        Shipment shipment;
        if (shippingMethod.equals("Standard")) {
            shipment = new StandardShipping();
            
        } else {
            shipment = new ExpressShipping();
        }
        
        shipment.calculateShippingCharge();
        
        return shipment;
    }
}
