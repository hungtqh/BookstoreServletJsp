package model.person;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 * The persistent class for the account database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Account.findAll", query="SELECT a FROM Account a WHERE a.role != 'Admin'"),
	@NamedQuery(name="Account.checkLogin", query="SELECT a FROM Account a WHERE a.username = :username AND a.password = :password"),
	@NamedQuery(name="Account.findByUsername", query="SELECT a FROM Account a WHERE a.username = :username"),
	@NamedQuery(name="Account.checkIsUsed", 
	query="SELECT a FROM Account a, Person p WHERE a.id = p.account.id AND a.id = :id"),
	@NamedQuery(name="Account.findAllUserAccount", query="SELECT a FROM Account a WHERE a.role = 'User' AND a.id NOT IN "
			+ "(SELECT p.account.id FROM Person p)"),
	@NamedQuery(name="Account.countAll", query="SELECT COUNT(a) FROM Account a WHERE a.role != 'Admin'"),
})
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String password;

	private String role;

	private String username;

	// bi-directional one-to-one association to Person
	@OneToOne(mappedBy = "account")
	private Person person;

	public Account() {
	}

	public Account(String username, String role, String password) {
		this.password = password;
		this.role = role;
		this.username = username;
	}

	public Account(int id, String username, String password, String role) {
		super();
		this.id = id;
		this.password = password;
		this.role = role;
		this.username = username;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}