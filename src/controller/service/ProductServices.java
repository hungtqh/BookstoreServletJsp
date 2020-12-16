package controller.service;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.dao.CategoryDAO;
import controller.dao.ProductDAO;
import model.product.Category;
import model.product.Product;

public class ProductServices {
	private ProductDAO productDAO;
	private CategoryDAO categoryDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public ProductServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		productDAO = new ProductDAO();
		categoryDAO = new CategoryDAO();
	}

	public void listProduct() throws ServletException, IOException {
		List<Product> listProduct = productDAO.listAll();
		List<Category> listCategory = categoryDAO.listAll();
		
		request.setAttribute("listProduct", listProduct);
		request.setAttribute("listCategory", listCategory);
		
		String listPage = "product_list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(listPage);
		dispatcher.forward(request, response);
	}
	
	// read list category and forward to the form
	public void showProductNewForm() throws ServletException, IOException {
		List<Category> listCategory = categoryDAO.listAll();
		request.setAttribute("listCategory", listCategory);
		
		String newPage = "product_form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(newPage);
		dispatcher.forward(request, response);
	}

	public void createProduct() throws ServletException, IOException {
		
		Product newProduct = new Product();
		readProductFields(newProduct);
		
		Product createdProduct = productDAO.create(newProduct);
		
		if (createdProduct.getId() > 0) {
			String message = "A new product has been created successfully.";
			request.setAttribute("message", message);
			listProduct();
		}
	}
	
	// read values from the form for the Product
	public void readProductFields(Product product) throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("category"));
		String name = request.getParameter("name").trim();
		int unitsInStock = Integer.parseInt(request.getParameter("unitsInStock"));
		String description = request.getParameter("description");
		float price = Float.parseFloat(request.getParameter("price"));

		product.setName(name);
		product.setUnitsInStock(unitsInStock);
		product.setDescription(description);
		product.setPrice(new BigDecimal(price));
		
		Category category = categoryDAO.get(categoryId);
		product.setCategory(category);

		// get image
		Part part = request.getPart("product_image"); // the ID of img
		if (part != null && part.getSize() > 0) {
			long size = part.getSize();
			byte[] imageBytes = new byte[(int) size];

			InputStream inputStream = part.getInputStream();
			inputStream.read(imageBytes);
			inputStream.close();

			product.setImage(imageBytes);
		}
	}

	public void editProduct() throws ServletException, IOException {
		int productId = Integer.parseInt(request.getParameter("id"));
		Product Product = productDAO.get(productId);
		List<Category> listCategory = categoryDAO.listAll();
		
		if (Product == null) {
			String msg = "Could not find product with ID " + productId + ". Product doesn't exist!";
			request.setAttribute("message", msg);
			
			listProduct();
			return;
		}
		
		request.setAttribute("listCategory", listCategory);
		request.setAttribute("product", Product);

		String editPage = "product_form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(editPage);
		dispatcher.forward(request, response);
	}

	public void updateProduct() throws ServletException, IOException {
		int productId = Integer.parseInt(request.getParameter("productId"));
		Product productById = productDAO.get(productId);
		if (productById == null) {
			String msg = "Could not find product with ID " + productId + ". Product doesn't exist!";
			request.setAttribute("message", msg);
			
			listProduct();
			return;
		}
		
		readProductFields(productById);
		
		productDAO.update(productById);
		String msg = "The Product has been updated successfully.";
		request.setAttribute("message", msg);
		listProduct();
		
	}

	public void deleteProduct() throws ServletException, IOException {
		int productId = Integer.parseInt(request.getParameter("id"));
		
		Product productById = productDAO.get(productId);
		
		if (productById == null) {
			String msg = "Could not find Product with ID = " + productId;
			request.setAttribute("message", msg);
		} else {
			productDAO.delete(productId);
			String msg = "Product has been deleted successfully!";
			request.setAttribute("message", msg);
		}
		
		listProduct();
	}

	public void listProductByCategory() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("id"));
		Category category = categoryDAO.get(categoryId);
		
		if (category == null) {
			String msg = "Sorry, the category is not available.";
			request.setAttribute("message", msg);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("frontend/message.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		List<Product> listProduct = productDAO.listByCategory(categoryId);
		
		request.setAttribute("listProduct", listProduct);
		request.setAttribute("category", category);
		
		String listPage = "frontend/Product_list_by_category.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(listPage);
		dispatcher.forward(request, response);
	}

	public void viewProductDetail() throws ServletException, IOException {
		int ProductId = Integer.parseInt(request.getParameter("id"));
		Product Product = productDAO.get(ProductId);
		
		if (Product == null) {
			String msg = "Sorry, the Product is not available.";
			request.setAttribute("message", msg);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("frontend/message.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		request.setAttribute("product", Product);
		
		String listPage = "frontend/product_detail.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(listPage);
		dispatcher.forward(request, response);
	}

	public void searchProduct() throws ServletException, IOException {
		String keyword = request.getParameter("keyword").trim();
		List<Product> searchResult = null;
		
		searchResult = productDAO.search(keyword);

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

	public void sellProduct() throws ServletException, IOException {
		int productID = Integer.parseInt(request.getParameter("id"));
		
		Product Product = productDAO.get(productID);
		
		if (Product == null) {
			String msg = "Could not find product with ID = " + productID;
			request.setAttribute("message", msg);
			listProduct();
		} else {
			String page = "item_form.jsp";
			request.setAttribute("product", Product);
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}
	}

	public void listProductByCategoryAdmin() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("category_id"));
		Category category = categoryDAO.get(categoryId);
		
		if (category == null) {
			String msg = "Sorry, the category is not available.";
			request.setAttribute("message", msg);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("frontend/message.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		List<Product> listProduct = productDAO.listByCategory(categoryId);
		List<Category> listCategory = categoryDAO.listAll();
		
		request.setAttribute("listCategory", listCategory);
		request.setAttribute("listProduct", listProduct);
		request.setAttribute("selectedCategory", category);
		
		String listPage = "product_list_by_category.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(listPage);
		dispatcher.forward(request, response);
	}
}
