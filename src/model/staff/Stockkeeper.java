package model.staff;

import java.io.Serializable;
import java.util.Date;

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

import model.person.Person;

/**
 * The persistent class for the stockkeeper database table.
 * 
 */
@Entity
@NamedQuery(name = "Stockkeeper.findAll", query = "SELECT s FROM Stockkeeper s")
public class Stockkeeper implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name = "join_date")
	private Date joinDate;

	// bi-directional one-to-one association to Person
	@OneToOne
	@JoinColumn(name = "person_id", nullable = false)
	private Person person;

	public Stockkeeper() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getJoinDate() {
		return this.joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}