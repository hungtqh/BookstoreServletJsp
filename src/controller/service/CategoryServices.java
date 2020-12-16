package controller.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.dao.CategoryDAO;
import controller.dao.ProductDAO;
import model.product.Category;

public class CategoryServices {
	private CategoryDAO categoryDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public CategoryServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		categoryDAO = new CategoryDAO();
	}

	public void listCategory() throws ServletException, IOException {
		List<Category> listCategory = categoryDAO.listAll();
		request.setAttribute("listCategory", listCategory);

		String listPage = "category_list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(listPage);
		dispatcher.forward(request, response);
	}

	public void createCategory() throws ServletException, IOException {
		String categoryName = request.getParameter("categoryName").trim();

		Category existCategory = categoryDAO.findByName(categoryName);

		if (existCategory != null) { 
			String msg = "Could not create category. A category with name " + categoryName + " already exists";
			request.setAttribute("message", msg);
		} else {
			categoryDAO.create(new Category(categoryName));
			request.setAttribute("message", "New category was created successfully!");
		}
		listCategory();
	}

	public void editCategory() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("id"));

		Category category = categoryDAO.get(categoryId);

		if (category == null) {
			String msg = "Could not find the category with ID = " + categoryId;
			request.setAttribute("message", msg);

			listCategory();
		} else {
			String editPage = "category_form.jsp";
			request.setAttribute("category", category);
			RequestDispatcher dispatcher = request.getRequestDispatcher(editPage);
			dispatcher.forward(request, response);
		}
	}

	public void updateCategory() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		String categoryName = request.getParameter("categoryName").trim();

		Category categoryByName = categoryDAO.findByName(categoryName);
		Category categoryById = categoryDAO.get(categoryId);
		
		if (categoryById == null) {
			String msg = "Could not find the category with ID = " + categoryId;
			request.setAttribute("message", msg);

		} else if (categoryByName != null && categoryByName.getId() != categoryId) {
			String msg = "Could not update category" + ". Category with " + categoryName + " already exists!";
			request.setAttribute("message", msg);

		} else {
			categoryById.setName(categoryName);
			categoryDAO.update(categoryById);
			String msg = "Category was updated successfully!";
			request.setAttribute("message", msg);
		}
		
		listCategory();
	}

	public void deleteCategory() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("id"));
		ProductDAO productDAO = new ProductDAO();
		long totalBook = productDAO.countByCategory(categoryId);
		Category categoryById = categoryDAO.get(categoryId);
		
		if (categoryById == null) {
			String msg = "Could not find category with ID = " + categoryId;
			request.setAttribute("message", msg);

		} else {
			if (totalBook == 0) {
				categoryDAO.delete(categoryId);
				String msg = "Category was deleted successfully!";
				request.setAttribute("message", msg);
			} else {
				String msg = "Could not delete category. The category still has some books!";
				request.setAttribute("message", msg);
			}
		}
		
		listCategory();
	}
}
