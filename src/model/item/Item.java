package model.item;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import model.order.OrderDetail;
import model.product.Product;
import model.review.Review;

/**
 * The persistent class for the item database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "Item.findByCategory", 
			query = "SELECT i FROM Item i, Product p, Category c WHERE i.product.id = p.id AND p.category.id = c.id AND c.id = :id"),
	
	@NamedQuery(name="Item.findAll", query="SELECT i FROM Item i ORDER BY i.id DESC"),
	
	@NamedQuery(name = "Item.search", query = "SELECT DISTINCT(i) FROM Item i, Product p WHERE p.id = i.product.id "
			+ "AND (p.name LIKE '%' || :keyword || '%'"
			+ " OR p.description LIKE '%' || :keyword || '%'" + ")"),
	
	@NamedQuery(name = "Item.listNew", query = "SELECT DISTINCT(i) FROM Item i, Product p "
			+ "WHERE i.product.id = p.id ORDER BY p.lastUpdatedTime DESC"),
	
	@NamedQuery(name="Item.countAll", query="SELECT COUNT(i) FROM Item i")
})
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	public Item(int id) {
		this.id = id;
	}

	@Column(name = "selling_price")
	private BigDecimal sellingPrice;

	// bi-directional many-to-one association to OrderDetail
	@OneToMany(mappedBy = "item")
	private List<OrderDetail> orderDetails;

	// bi-directional many-to-one association to Review
	@OneToMany(mappedBy = "item", fetch = FetchType.EAGER)
	private List<Review> reviews;

	// bi-directional one-to-one association to Product
	@OneToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	public Item() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getSellingPrice() {
		return this.sellingPrice;
	}

	public void setSellingPrice(BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public List<OrderDetail> getOrderDetails() {
		return this.orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public OrderDetail addOrderDetail(OrderDetail orderDetail) {
		getOrderDetails().add(orderDetail);
		orderDetail.setItem(this);

		return orderDetail;
	}

	public OrderDetail removeOrderDetail(OrderDetail orderDetail) {
		getOrderDetails().remove(orderDetail);
		orderDetail.setItem(null);

		return orderDetail;
	}

	public List<Review> getReviews() {
		return this.reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public Review addReview(Review review) {
		getReviews().add(review);
		review.setItem(this);

		return review;
	}

	public Review removeReview(Review review) {
		getReviews().remove(review);
		review.setItem(null);

		return review;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Transient
	public float getAverageRating() {
		float sum = 0.0f;
		float averageRating = 0.0f;

		if (reviews.isEmpty()) {
			return 0.0f;
		}

		for (Review review : reviews) {
			sum += review.getRating();
		}

		averageRating = sum / reviews.size();

		return averageRating;
	}

	@Transient
	public String getRatingString(float averageRating) {
		String result = "";

		int numberOfStarsOn = (int) averageRating;

		// generating state for whole star rating
		for (int i = 1; i <= numberOfStarsOn; i++) {
			result += "on";
			if (i < 5)
				result += ",";
		}

		// generate state for half star rating
		if (averageRating > numberOfStarsOn) {
			result += "half";
			if (averageRating < 4.0f)
				result += ",";
		}

		// generate state for no rating star
		int next = 0;
		if (averageRating > numberOfStarsOn) {
			next = numberOfStarsOn + 2;
		} else {
			next = numberOfStarsOn + 1;
		}
		
		for (int j = next; j <= 5; j++) {
			result += "off";
			if (j < 5)
				result += ",";
		}

		return result;
	}
	
	@Transient 
	public String getRatingStars() {
		float averageRating = getAverageRating();
		
		return getRatingString(averageRating);
	}

}