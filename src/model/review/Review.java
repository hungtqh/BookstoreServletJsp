package model.review;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import model.customer.Customer;
import model.item.Item;


/**
 * The persistent class for the review database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Review.findAll", query="SELECT r FROM Review r ORDER BY r.reviewTime DESC"),
	
	@NamedQuery(name="Review.findByCustomerAndItem", 
			query="SELECT r FROM Review r WHERE r.customer.id = :customerID AND r.item.id = :itemID"),
	
	@NamedQuery(name="Review.countAll", query="SELECT COUNT(r) FROM Review r")
})

public class Review implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String comment;

	private String headline;

	private float rating;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="review_time")
	private Date reviewTime;

	//bi-directional many-to-one association to Item
	@ManyToOne
	private Item item;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	private Customer customer;

	public Review() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getHeadline() {
		return this.headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public float getRating() {
		return this.rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public Date getReviewTime() {
		return this.reviewTime;
	}

	public void setReviewTime(Date reviewTime) {
		this.reviewTime = reviewTime;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	@Transient
	public String getStars() {
		String result = "";

		// generating state for whole star rating
		for (int i = 1; i <= rating; i++) {
			result += "on";
			if (i < 5)
				result += ",";
		}

		// generate state for no rating star
		int next = (int) rating + 1;
		
		for (int j = next; j <= 5; j++) {
			result += "off";
			if (j < 5)
				result += ",";
		}

		return result;
	}

}