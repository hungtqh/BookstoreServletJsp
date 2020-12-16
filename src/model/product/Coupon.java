package model.product;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the coupon database table.
 * 
 */
@Entity
@NamedQuery(name="Coupon.findAll", query="SELECT c FROM Coupon c")
public class Coupon implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String code;

	@Column(name="discount_rate")
	private float discountRate;

	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;

	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private Date startDate;

	//bi-directional many-to-one association to Category
	@ManyToOne
	private Category category;

	public Coupon() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public float getDiscountRate() {
		return this.discountRate;
	}

	public void setDiscountRate(float discountRate) {
		this.discountRate = discountRate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}