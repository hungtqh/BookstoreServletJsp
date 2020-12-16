package controller.frontend;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.dao.ItemDAO;
import model.item.Item;
import model.shoppingcart.ShoppingCart;

@WebServlet({ "/home", "" })
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ItemDAO itemDAO = new ItemDAO();
		List<Item> newItems = itemDAO.listNewItem();
		List<Item> favouriteItems = itemDAO.listFavouriteItems();
		
		Object cartObject = request.getSession().getAttribute("cart");

		if (cartObject == null) {
			ShoppingCart cart = new ShoppingCart();
			request.getSession().setAttribute("cart", cart);
		}
		
		request.setAttribute("favouriteItems", favouriteItems);
		request.setAttribute("newItems", newItems);
		String homepage = "frontend/index.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(homepage);
		dispatcher.forward(request, response);
	}

}
