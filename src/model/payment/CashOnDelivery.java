package model.payment;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * The persistent class for the cash_on_delivery database table.
 * 
 */
@Entity
@Table(name="cash_on_delivery")
@NamedQuery(name="CashOnDelivery.findAll", query="SELECT c FROM CashOnDelivery c")
public class CashOnDelivery implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="cash_tendered")
	private BigDecimal cashTendered;

	//bi-directional one-to-one association to Payment
	@OneToOne
	@JoinColumn(name="payment_id", nullable = false)
	private Payment payment;

	public CashOnDelivery() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getCashTendered() {
		return this.cashTendered;
	}

	public void setCashTendered(BigDecimal cashTendered) {
		this.cashTendered = cashTendered;
	}

	public Payment getPayment() {
		return this.payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

}