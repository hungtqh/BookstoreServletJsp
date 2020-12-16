package model.person;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the address database table.
 * 
 */
@Entity
@NamedQuery(name="Address.findAll", query="SELECT a FROM Address a")
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="address_detail")
	private String addressDetail;

	private String city;

	private String province;


	
	public Address(String city, String province, String addressDetail) {
		super();
		this.addressDetail = addressDetail;
		this.city = city;
		this.province = province;
	}

	//bi-directional one-to-one association to Person
	@OneToOne(mappedBy = "address")
	private Person person;

	public Address() {
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

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}