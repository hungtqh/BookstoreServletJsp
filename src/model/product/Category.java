package model.product;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * The persistent class for the category database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c"),
	@NamedQuery(name="Category.findByName", query="SELECT c FROM Category c WHERE c.name = :name")
})
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	// bi-directional many-to-one association to Coupon
	@OneToMany(mappedBy = "category")
	private List<Coupon> coupons;

	// bi-directional many-to-one association to Product
	@OneToMany(mappedBy = "category")
	private List<Product> products;

	public Category() {
	}

	public Category(String name) {
		super();
		this.name = name;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Coupon> getCoupons() {
		return this.coupons;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}

	public Coupon addCoupon(Coupon coupon) {
		getCoupons().add(coupon);
		coupon.setCategory(this);

		return coupon;
	}

	public Coupon removeCoupon(Coupon coupon) {
		getCoupons().remove(coupon);
		coupon.setCategory(null);

		return coupon;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setCategory(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setCategory(null);

		return product;
	}

}