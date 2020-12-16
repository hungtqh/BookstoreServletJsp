package controller.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.dao.ItemDAO;
import controller.dao.ReviewDAO;
import model.customer.Customer;
import model.item.Item;
import model.review.Review;

public class ReviewServices {
	private ReviewDAO reviewDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public ReviewServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		reviewDAO = new ReviewDAO();
	}

	public void listAllReview() throws ServletException, IOException {
		List<Review> listReview = reviewDAO.listAll();

		request.setAttribute("listReview", listReview);

		String listPage = "review_list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(listPage);
		dispatcher.forward(request, response);
	}

	public void editReview() throws ServletException, IOException {
		int reviewId = Integer.parseInt(request.getParameter("id"));

		Review review = reviewDAO.get(reviewId);

		if (review == null) {
			String msg = "Could not find the review with ID = " + reviewId;
			request.setAttribute("message", msg);
			listAllReview();
		} else {
			request.setAttribute("review", review);
			String editPage = "review_form.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(editPage);
			dispatcher.forward(request, response);
		}
	}
	
	public void updateReview() throws ServletException, IOException {
		int reviewId = Integer.parseInt(request.getParameter("reviewId"));

		Review review = reviewDAO.get(reviewId);

		if (review == null) {
			String msg = "Could not find the review with ID = " + reviewId;
			request.setAttribute("message", msg);
			listAllReview();
			return;
		}

		String headline = request.getParameter("headline");
		String comment = request.getParameter("comment");

		review.setHeadline(headline);
		review.setComment(comment);

		reviewDAO.update(review);
		String msg = "The review has been updated successfully!";
		request.setAttribute("message", msg);
		listAllReview();

	}
	
	public void deleteReview() throws ServletException, IOException {
		int reviewId = Integer.parseInt(request.getParameter("id"));
		Review review = reviewDAO.get(reviewId);

		if (review == null) {
			String msg = "Could not find the review with ID = " + reviewId;
			request.setAttribute("message", msg);
			listAllReview();
			return;
		}
		
		reviewDAO.delete(reviewId);
		String msg = "The review has been deleted successfully!";
		request.setAttribute("message", msg);
		listAllReview();
	}
	
	public void showReviewForm() throws ServletException, IOException {
		int itemID = Integer.parseInt(request.getParameter("item_id"));
		Customer customer = (Customer) (request.getSession().getAttribute("loggedCustomer"));
		int customerId = customer.getId();
		
		ItemDAO itemDAO = new ItemDAO();
		Item item = itemDAO.get(itemID);
		request.getSession().setAttribute("item", item);
		
		Review existReview = reviewDAO.findByCustomerAndItem(customerId, itemID);
		
		String targetPage = "frontend/review_form.jsp";
		
		if (existReview != null) {
			request.setAttribute("review", existReview);
			targetPage = "frontend/review_info.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(targetPage);
		dispatcher.forward(request, response);
	}

	public void submitReview() throws ServletException, IOException {
		int itemID = Integer.parseInt(request.getParameter("itemID"));
		Customer customer = (Customer) request.getSession(false).getAttribute("loggedCustomer");
		Review existReview = reviewDAO.findByCustomerAndItem(customer.getId(), itemID);
		
		if (existReview != null) {
			request.setAttribute("review", existReview);
			String targetPage = "frontend/review_info.jsp";
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(targetPage);
			dispatcher.forward(request, response);
			
			return;
		}
		
		int rating = (int) Float.parseFloat(request.getParameter("rating"));
		String headline = request.getParameter("headline");
		String comment = request.getParameter("comment");
		
		Review newReview = new Review();
		newReview.setHeadline(headline);
		newReview.setComment(comment);
		newReview.setRating(rating); 
		
		Item item = new Item();
		item.setId(itemID);
		newReview.setItem(item);
		
		
		newReview.setCustomer(customer);
		
		reviewDAO.create(newReview);
		
		String messagePage = "frontend/review_done.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(messagePage);
		dispatcher.forward(request, response);
	}
}
