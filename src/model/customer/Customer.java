package model.customer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import model.order.Order;
import model.person.Person;
import model.review.Review;


/**
 * The persistent class for the customer database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c ORDER BY c.registerDate DESC"),
	@NamedQuery(name="Customer.countAll", query="SELECT COUNT(c) FROM Customer c")
})
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="register_date")
	private Date registerDate;

	//bi-directional many-to-one association to CustomerShippingAddress
	@OneToMany(mappedBy="customer")
	private List<CustomerShippingAddress> customerShippingAddresses;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="customer")
	private List<Order> orders;

	//bi-directional many-to-one association to Review
	@OneToMany(mappedBy="customer")
	private List<Review> reviews;

	//bi-directional one-to-one association to Person
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="person_id", nullable = false)
	private Person person;

	public Customer() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getRegisterDate() {
		return this.registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public List<CustomerShippingAddress> getCustomerShippingAddresses() {
		return this.customerShippingAddresses;
	}

	public void setCustomerShippingAddresses(List<CustomerShippingAddress> customerShippingAddresses) {
		this.customerShippingAddresses = customerShippingAddresses;
	}

	public CustomerShippingAddress addCustomerShippingAddress(CustomerShippingAddress customerShippingAddress) {
		getCustomerShippingAddresses().add(customerShippingAddress);
		customerShippingAddress.setCustomer(this);

		return customerShippingAddress;
	}

	public CustomerShippingAddress removeCustomerShippingAddress(CustomerShippingAddress customerShippingAddress) {
		getCustomerShippingAddresses().remove(customerShippingAddress);
		customerShippingAddress.setCustomer(null);

		return customerShippingAddress;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setCustomer(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setCustomer(null);

		return order;
	}

	public List<Review> getReviews() {
		return this.reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public Review addReview(Review review) {
		getReviews().add(review);
		review.setCustomer(this);

		return review;
	}

	public Review removeReview(Review review) {
		getReviews().remove(review);
		review.setCustomer(null);

		return review;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}