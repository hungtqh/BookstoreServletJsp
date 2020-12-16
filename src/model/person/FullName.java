package model.person;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the full_name database table.
 * 
 */
@Entity
@Table(name = "full_name")
@NamedQuery(name = "FullName.findAll", query = "SELECT f FROM FullName f")
public class FullName implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	// bi-directional one-to-one association to Person
	@OneToOne(mappedBy = "fullName")
	private Person person;

	public FullName() {
	}

	public FullName(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}