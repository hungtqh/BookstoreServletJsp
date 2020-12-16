package model.customer;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the customer_shipping_address database table.
 * 
 */
@Entity
@Table(name="customer_shipping_address")
@NamedQueries({
	@NamedQuery(name="CustomerShippingAddress.findAll", query="SELECT c FROM CustomerShippingAddress c"),
	@NamedQuery(name="CustomerShippingAddress.findAllByCustomer", 
	query="SELECT s FROM CustomerShippingAddress s, Customer c WHERE s.customer.id = c.id"
			+ " AND c.id = :customerID")
})
public class CustomerShippingAddress implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="address_detail")
	private String addressDetail;

	private String city;

	private String province;

	@Column(name="recipient_name")
	private String recipientName;

	@Column(name="recipient_phone")
	private String recipientPhone;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	private Customer customer;

	public CustomerShippingAddress() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddressDetail() {
		return this.addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getRecipientName() {
		return this.recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getRecipientPhone() {
		return this.recipientPhone;
	}

	public void setRecipientPhone(String recipientPhone) {
		this.recipientPhone = recipientPhone;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}