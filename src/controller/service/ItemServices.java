package controller.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.dao.CategoryDAO;
import controller.dao.ItemDAO;
import controller.dao.ProductDAO;
import model.item.Item;
import model.product.Category;
import model.product.Product;

public class ItemServices {
	private ProductDAO productDAO;
	private ItemDAO itemDAO;
	private CategoryDAO categoryDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public ItemServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		productDAO = new ProductDAO();
		itemDAO = new ItemDAO();
		categoryDAO = new CategoryDAO();
	}

	public void listItem() throws ServletException, IOException {
		List<Item> listItem = itemDAO.listAll();
		request.setAttribute("listItem", listItem);

		String listPage = "item_list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(listPage);
		dispatcher.forward(request, response);
	}

	public void createItem() throws ServletException, IOException {
		int productID = Integer.parseInt(request.getParameter("productId"));
		float sellingPrice = Float.parseFloat(request.getParameter("selling_price").trim());

		Product product = productDAO.get(productID);
		Item newItem = new Item();
		newItem.setSellingPrice(new BigDecimal(sellingPrice));
		product.setActive(true);
		newItem.setProduct(product);

		itemDAO.create(newItem);
		productDAO.update(product);

		String message = "A new item has been created successfully.";
		request.setAttribute("message", message);
		
		ProductServices services = new ProductServices(request, response);
		services.listProduct();
	}

	public void editItem() throws ServletException, IOException {
		int itemId = Integer.parseInt(request.getParameter("id"));

		Item item = itemDAO.get(itemId);

		if (item == null) {
			String msg = "Could not find item with ID = " + itemId;
			request.setAttribute("message", msg);
			listItem();
		} else {
			request.setAttribute("item", item);
			String page = "item_form.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}
	}

	public void listItemByCategory() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("id"));
		Category category = categoryDAO.get(categoryId);

		if (category == null) {
			String msg = "Sorry, the category is not available.";
			request.setAttribute("message", msg);

			RequestDispatcher dispatcher = request.getRequestDispatcher("frontend/message.jsp");
			dispatcher.forward(request, response);
			return;
		}

		List<Item> listItem = itemDAO.listByCategory(categoryId);

		request.setAttribute("listItem", listItem);
		request.setAttribute("category", category);

		String listPage = "frontend/item_list_by_category.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(listPage);
		dispatcher.forward(request, response);
	}

	public void viewItemDetail() throws ServletException, IOException {
		int itemID = Integer.parseInt(request.getParameter("id"));
		Item item = itemDAO.get(itemID);

		if (item == null) {
			String msg = "Sorry, the Product is not available.";
			request.setAttribute("message", msg);

			RequestDispatcher dispatcher = request.getRequestDispatcher("frontend/message.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		request.setAttribute("item", item);
		
		String listPage = "frontend/item_detail.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(listPage);
		dispatcher.forward(request, response);
	}

	public void updateItem() throws ServletException, IOException {
		int itemID = Integer.parseInt(request.getParameter("itemId"));
		float sellingPrice = Float.parseFloat(request.getParameter("selling_price"));
		
		Item item = itemDAO.get(itemID);
		
		if (item == null) {
			String msg = "Could not find item with ID = " + itemID;
			request.setAttribute("message", msg);
			
		} else {
			item.setSellingPrice(new BigDecimal(sellingPrice));
			itemDAO.update(item);
			
			String msg = "The item has been updated successfully.";
			request.setAttribute("message", msg);
		}
		
		listItem();
		
	}

	public void removeItem() throws ServletException, IOException {
		int itemID = Integer.parseInt(request.getParameter("id"));
		System.out.println(itemID);
		Item item = itemDAO.get(itemID);
		
		if (item == null) {
			String msg = "Could not find item with ID = " + itemID;
			request.setAttribute("message", msg);
			
		} else {
			Product product = item.getProduct();
			product.setActive(false);
			productDAO.update(product);
			itemDAO.delete(itemID);
			String msg = "The item has been removed successfully.";
			request.setAttribute("message", msg);
		}
		
		listItem();
	}
	
	public void searchItem() throws ServletException, IOException {
		String keyword = request.getParameter("keyword").trim();
		List<Item> searchResult = null;
		
		searchResult = itemDAO.search(keyword);

		if (searchResult.size() == 0) {
			String msg = "No search results for '" + keyword + "'";
			request.setAttribute("message", msg);
		} else {
			request.setAttribute("keyword", keyword);
			request.setAttribute("searchResult", searchResult);
		}
		
		String resultPage = "frontend/search_result.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(resultPage);
		dispatcher.forward(request, response);
	}
}