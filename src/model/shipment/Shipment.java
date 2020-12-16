package model.shipment;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import model.order.Order;


/**
 * The persistent class for the shipment database table.
 * 
 */
@Entity
@NamedQuery(name="Shipment.findAll", query="SELECT s FROM Shipment s")
public abstract class Shipment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	protected BigDecimal charge;

	protected String name;

	//bi-directional one-to-one association to Order
	@OneToOne(mappedBy = "shipment")
	private Order order;

	public Shipment() {
	}
	
	public abstract void calculateShippingCharge();

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getCharge() {
		return this.charge;
	}

	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}