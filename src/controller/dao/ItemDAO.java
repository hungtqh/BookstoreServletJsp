package controller.dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import model.item.Item;

public class ItemDAO extends JpaDAO<Item> implements GenericDAO<Item> {
	
	public ItemDAO() {
	}
	
	public Item create(Item item) {
		return super.create(item);
	}
	
	public Item update(Item item) {
		return super.update(item);
	}
	
	@Override
	public Item get(Object itemId) {
		return super.find(Item.class, itemId);
	}

	@Override
	public void delete(Object itemId) {
		super.delete(Item.class, itemId);
	}

	@Override
	public List<Item> listAll() {
		return super.findWithNamedQuery("Item.findAll");
	}
	
	public Item findByTitle(String title) {
		List<Item> list = (List<Item>) super.findWithNamedQuery("Item.findByTitle", "title", title);
	
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		
		return null;
	}
	
	public List<Item> listByCategory(int categoryId) {
		return super.findWithNamedQuery("Item.findByCategory", "id", categoryId);
	}

	// list 4 newest items to display on the homepage
	public List<Item> listNewItem() {
		return super.findWithNamedQuery("Item.listNew", 0, 4);
	}
	
	public List<Item> search(String keyword) {
		return super.findWithNamedQuery("Item.search", "keyword", keyword);
	}
	
	@Override
	public long count() {
		return super.countWithNamedQuery("Item.countAll");
	}


	public List<Item> listFavouriteItems() {
		List<Item> items = super.findWithNamedQuery("Item.findAll");
		items.sort(new Comparator<Item>() {		
		    @Override
		    public int compare(Item i1, Item i2) {
		    	float result = i2.getAverageRating() - i1.getAverageRating();
		    	int cmp = 0;
		    	if (result > 0) {
		    		cmp = 1;
		    	} else if (result < 0) {
		    		cmp = -1;
		    	}
		        return cmp;
		     }
		});
		
		List<Item> favouriteItems = new ArrayList<>();
		for (int i = 0; i < items.size(); i++) {
			if (i == 4) break;
			favouriteItems.add(items.get(i));
		}
		
		return favouriteItems;
	}
}
