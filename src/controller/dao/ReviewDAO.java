package controller.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.review.Review;

public class ReviewDAO extends JpaDAO<Review> implements GenericDAO<Review> {

	@Override
	public Review create(Review review) {
		review.setReviewTime(new Date());
		return super.create(review);
	}

	@Override
	public Review update(Review review) {
		return super.update(review);
	}

	@Override
	public Review get(Object reviewId) { 
		return super.find(Review.class, reviewId);
	}

	@Override
	public void delete(Object reviewId) {
		super.delete(Review.class, reviewId);
	}

	@Override
	public List<Review> listAll() { 
		return super.findWithNamedQuery("Review.findAll");
	}

	@Override
	public long count() { 
		return super.countWithNamedQuery("Review.countAll");
	}
	
	public long countReviewByBook(int bookId) {
		return super.countWithNamedQuery("Review.countReviewByBook", "bookId", bookId);
	}
 	
	public long countReviewByCustomer(int customerId) {
		return super.countWithNamedQuery("Review.countReviewByCustomer", "customerId", customerId);
	}
	
	public Review findByCustomerAndItem(int customerID, int itemID) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("customerID", customerID);
		parameters.put("itemID", itemID);
		
		List<Review> result = super.findWithNamedQuery("Review.findByCustomerAndItem", parameters);
		
		if (result != null && result.size() == 1) {
			return result.get(0);
		}
		
		return null;
	}

	public List<Review> listNewReview() {
		List<Review> listNewReviews = super.findWithNamedQuery("Review.findAll", 0, 4);

		return listNewReviews;
	}
}
