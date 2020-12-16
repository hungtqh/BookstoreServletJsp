package model.payment;

import java.io.Serializable;

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
 * The persistent class for the credit_card database table.
 * 
 */
@Entity
@Table(name="credit_card")
@NamedQuery(name="CreditCard.findAll", query="SELECT c FROM CreditCard c")
public class CreditCard implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="card_number")
	private String cardNumber;

	@Column(name="card_type")
	private String cardType;

	@Column(name="exp_date")
	private String expDate;

	@Column(name="holder_name")
	private String holderName;

	//bi-directional one-to-one association to Payment
	@OneToOne
	@JoinColumn(name="payment_id", nullable = false)
	private Payment payment;

	public CreditCard() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCardNumber() {
		return this.cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardType() {
		return this.cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getExpDate() {
		return this.expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public String getHolderName() {
		return this.holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public Payment getPayment() {
		return this.payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

}