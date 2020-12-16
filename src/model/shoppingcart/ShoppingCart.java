package model.shoppingcart;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import model.item.Item;

public class ShoppingCart {
	private Map<Item, Integer> cart = new HashMap<>();

	public ShoppingCart() {
	}

	public void addItem(Item item) {
		if (cart.containsKey(item)) {
			Integer quantity = cart.get(item) + 1;
			cart.put(item, quantity);
		} else {
			cart.put(item, 1);
		}
	}

	public Map<Item, Integer> getItems() {
		return this.cart;
	}

	public void removeItem(Item item) {
		cart.remove(item);
	}

	public int getTotalQuantity() {
		int total = 0;

		Iterator<Item> iterator = cart.keySet().iterator();

		while (iterator.hasNext()) {
			Item item = iterator.next();
			Integer quantity = cart.get(item);
			total += quantity;
		}

		return total;
	}

	public double getTotalAmount() {
		double total = 0;

		Iterator<Item> iterator = cart.keySet().iterator();

		while (iterator.hasNext()) {
			Item item = iterator.next();
			Integer quantity = cart.get(item);
			total += item.getSellingPrice().floatValue() * quantity;
		}

		return total;
	}

	public int getTotalItems() {
		return cart.size();
	}

	public void updateCart(int[] itemIds, int[] quantities) {
		for (int i = 0; i < itemIds.length; i++) {
			Item key = new Item(itemIds[i]);
			Integer value = quantities[i];

			cart.put(key, value);
		}
	}

	public void clear() {
		cart.clear();
	}
}
