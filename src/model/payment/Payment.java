package model.payment;

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
 * The persistent class for the payment database table.
 * 
 */
@Entity
@NamedQuery(name="Payment.findAll", query="SELECT p FROM Payment p")
public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private BigDecimal amount;

	private String name;

	//bi-directional one-to-one association to CashOnDelivery
	@OneToOne(mappedBy = "payment")
	private CashOnDelivery cashOnDelivery;

	//bi-directional one-to-one association to CreditCard
	@OneToOne(mappedBy = "payment")
	private CreditCard creditCard;

	//bi-directional one-to-one association to Order
	@OneToOne(mappedBy = "payment")
	private Order order;

	public Payment() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CashOnDelivery getCashOnDelivery() {
		return this.cashOnDelivery;
	}

	public void setCashOnDelivery(CashOnDelivery cashOnDelivery) {
		this.cashOnDelivery = cashOnDelivery;
	}

	public CreditCard getCreditCard() {
		return this.creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}