package model.person;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import model.customer.Customer;
import model.staff.Seller;
import model.staff.Stockkeeper;


/**
 * The persistent class for the person database table.
 * 
 */
@Entity
@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="date_of_birth")
	private Date dateOfBirth;

	private String email;

	private String gender;

	private String phone;

	//bi-directional one-to-one association to Customer
	@OneToOne(mappedBy="person")
	private Customer customer;

	//bi-directional one-to-one association to Address
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="address_id", nullable = false)
	private Address address;

	//bi-directional one-to-one association to Account
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="account_id", nullable = false)
	private Account account;

	//bi-directional one-to-one association to FullName
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="full_name_id", nullable = false)
	private FullName fullName;

	//bi-directional one-to-one association to Seller
	@OneToOne(mappedBy = "person")
	private Seller seller;

	//bi-directional one-to-one association to Stockkeeper
	@OneToOne(mappedBy = "person")
	private Stockkeeper stockkeeper;

	public Person() {
	}

	public Person(Date dateOfBirth, String email, String gender, String phone) {
		super();
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.gender = gender;
		this.phone = phone;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public FullName getFullName() {
		return this.fullName;
	}

	public void setFullName(FullName fullName) {
		this.fullName = fullName;
	}

	public Seller getSeller() {
		return this.seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public Stockkeeper getStockkeeper() {
		return this.stockkeeper;
	}

	public void setStockkeeper(Stockkeeper stockkeeper) {
		this.stockkeeper = stockkeeper;
	}

}