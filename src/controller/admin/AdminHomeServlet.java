package controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.dao.AccountDAO;
import controller.dao.CustomerDAO;
import controller.dao.ItemDAO;
import controller.dao.ProductDAO;
import controller.dao.ReviewDAO;
import model.review.Review;

@WebServlet({ "/admin/home", "/admin/" })
public class AdminHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AccountDAO accountDAO = new AccountDAO();
		long totalAccounts = accountDAO.count();
		CustomerDAO customerDAO = new CustomerDAO();
		long totalCustomers = customerDAO.count();
		ProductDAO productDAO = new ProductDAO();
		long totalProducts = productDAO.count();
		ItemDAO itemDAO = new ItemDAO();
		long totalItems = itemDAO.count();
		ReviewDAO reviewDAO = new ReviewDAO();
		long totalReviews = reviewDAO.count();
		List<Review> listNewReviews = reviewDAO.listNewReview();

		request.setAttribute("totalAccounts", totalAccounts);
		request.setAttribute("totalCustomers", totalCustomers);
		request.setAttribute("totalProducts", totalProducts);
		request.setAttribute("totalItems", totalItems);
		request.setAttribute("totalReviews", totalReviews);
		request.setAttribute("listNewReview", listNewReviews);

		String homepage = "index.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(homepage);
		dispatcher.forward(request, response);
	}

}
