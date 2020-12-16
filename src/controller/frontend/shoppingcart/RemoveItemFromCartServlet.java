package controller.frontend.shoppingcart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.dao.ItemDAO;
import model.item.Item;
import model.shoppingcart.ShoppingCart;

@WebServlet("/remove_from_cart")
public class RemoveItemFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int itemId = Integer.parseInt(request.getParameter("item_id"));
		ItemDAO itemDAO = new ItemDAO();
		Item item = itemDAO.get(itemId);
		
		ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
		if (cart.getTotalItems() > 0) {
			cart.removeItem(item);
		}
		
		String cartPage = request.getContextPath().concat("/view_cart");
		response.sendRedirect(cartPage);
	}

}
