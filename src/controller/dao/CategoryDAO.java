package controller.dao;

import java.util.List;

import model.product.Category;

public class CategoryDAO extends JpaDAO<Category> implements GenericDAO<Category> {

	public CategoryDAO() {
	}

	public Category findByName(String categoryName) {
		List<Category> categories = super.findWithNamedQuery("Category.findByName", "name", categoryName);
		
		if (categories != null && categories.size() == 1) {
			return categories.get(0);
		}
		
		return null;
	}
	
	@Override
	public Category create(Category category) {
		return super.create(category);
	}

	@Override
	public Category update(Category category) {
		return super.update(category);
	}

	@Override
	public Category get(Object categoryId) {
		return super.find(Category.class, categoryId);
	}

	@Override
	public void delete(Object categoryId) {
		super.delete(Category.class, categoryId);
	}

	@Override
	public List<Category> listAll() {
		return super.findWithNamedQuery("Category.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Category.countAll");
	}

}
