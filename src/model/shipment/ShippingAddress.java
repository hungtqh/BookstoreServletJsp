package model.shipment;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import model.order.Order;

/**
 * The persistent class for the shipping_address database table.
 * 
 */
@Entity
@Table(name = "shipping_address")
@NamedQuery(name = "ShippingAddress.findAll", query = "SELECT s FROM ShippingAddress s")
public class ShippingAddress implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "address_detail")
	private String addressDetail;

	private String city;

	private String province;

	@Column(name = "recipient_name")
	private String recipientName;

	@Column(name = "recipient_phone")
	private String recipientPhone;

	// bi-directional one-to-one association to Order
	@OneToOne(mappedBy = "shippingAddress")
	private Order order;

	public ShippingAddress() {
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
		return province;
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

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}