package controller.dao;

import java.util.Date;
import java.util.List;

import model.product.Product;

public class ProductDAO extends JpaDAO<Product> implements GenericDAO<Product> {
	
	public ProductDAO() {
	}
	
	public Product create(Product product) {
		product.setLastUpdatedTime(new Date());
		return super.create(product);
	}
	
	public Product update(Product product) {
		product.setLastUpdatedTime(new Date());
		return super.update(product);
	}
	
	@Override
	public Product get(Object productId) {
		return super.find(Product.class, productId);
	}

	@Override
	public void delete(Object productId) {
		super.delete(Product.class, productId);
	}

	@Override
	public List<Product> listAll() {
		return super.findWithNamedQuery("Product.findAll");
	}
	
	public Product findByName(String name) {
		List<Product> list = (List<Product>) super.findWithNamedQuery("Product.findByName", "name", name);
	
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		
		return null;
	}
	
	public List<Product> listByCategory(int categoryId) {
		return super.findWithNamedQuery("Product.findByCategory", "id", categoryId);
	}

	// list 4 newest Products to display on the homepage
	public List<Product> listNewProduct() {
		return super.findWithNamedQuery("Product.listNew", 0, 4);
	}
	
	public List<Product> search(String keyword) {
		return super.findWithNamedQuery("Product.search", "keyword", keyword);
	}
	
	@Override
	public long count() {
		return super.countWithNamedQuery("Product.countAll");
	}
	
	public long countByCategory(int categoryId) {
		return super.countWithNamedQuery("Product.countByCategory", "id", categoryId);
	}
}
