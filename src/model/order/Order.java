package model.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import model.customer.Customer;
import model.payment.Payment;
import model.shipment.Shipment;
import model.shipment.ShippingAddress;


/**
 * The persistent class for the order database table.
 * 
 */
@Entity
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String note;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="order_time")
	private Date orderTime;

	private String status;

	private BigDecimal total;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	private Customer customer;

	//bi-directional many-to-one association to OrderDetail
	@OneToMany(mappedBy="order")
	private List<OrderDetail> orderDetails;

	//bi-directional one-to-one association to Payment
	@OneToOne
	@JoinColumn(name="payment_id", nullable = false)
	private Payment payment;

	//bi-directional one-to-one association to ShippingAddress
	@OneToOne
	@JoinColumn(name="shipping_address_id", nullable = false)
	private ShippingAddress shippingAddress;

	//bi-directional one-to-one association to Shipment
	@OneToOne
	@JoinColumn(name="shipping_method_id", nullable = false)
	private Shipment shipment;

	public Order() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getOrderTime() {
		return this.orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<OrderDetail> getOrderDetails() {
		return this.orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public OrderDetail addOrderDetail(OrderDetail orderDetail) {
		getOrderDetails().add(orderDetail);
		orderDetail.setOrder(this);

		return orderDetail;
	}

	public OrderDetail removeOrderDetail(OrderDetail orderDetail) {
		getOrderDetails().remove(orderDetail);
		orderDetail.setOrder(null);

		return orderDetail;
	}

	public Payment getPayment() {
		return this.payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public ShippingAddress getShippingAddress() {
		return this.shippingAddress;
	}

	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Shipment getShipment() {
		return this.shipment;
	}

	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}

}