package model.product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Base64;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import model.item.Item;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p ORDER BY p.lastUpdatedTime DESC"),
	@NamedQuery(name = "Product.findByName", query = "SELECT p FROM Product p Where p.name = :name"),
	@NamedQuery(name = "Product.countAll", query = "SELECT COUNT(p) FROM Product p"),
	@NamedQuery(name = "Product.countByCategory", query = "SELECT COUNT(p) FROM Product p JOIN "
			+ "Category c ON p.category.id = c.id AND c.id = :id"),
	@NamedQuery(name = "Product.findByCategory", query = "SELECT p FROM Product p JOIN Category c ON p.category.id = c.id AND c.id = :id"),
	@NamedQuery(name = "Product.listNew", query = "SELECT p FROM Product p ORDER BY p.lastUpdatedTime DESC"),
	@NamedQuery(name = "Product.search", query = "SELECT p FROM Product p WHERE p.name LIKE '%' || :keyword || '%'"
			+ " OR p.description LIKE '%' || :keyword || '%'") 
})
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private boolean active;

	@Lob
	private String description;

	@Lob
	private byte[] image;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_updated_time")
	private Date lastUpdatedTime;

	private String name;

	private BigDecimal price;

	@Column(name="units_in_stock")
	private int unitsInStock;

	//bi-directional many-to-one association to Category
	@ManyToOne
	private Category category;

	//bi-directional one-to-one association to Item
	@OneToOne(mappedBy = "product")
	private Item item;

	public Product() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getImage() {
		return this.image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Date getLastUpdatedTime() {
		return this.lastUpdatedTime;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getUnitsInStock() {
		return this.unitsInStock;
	}

	public void setUnitsInStock(int unitsInStock) {
		this.unitsInStock = unitsInStock;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
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
		Product other = (Product) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Transient
	public String getBase64Image() {
		return Base64.getEncoder().encodeToString(this.image);
	}
}