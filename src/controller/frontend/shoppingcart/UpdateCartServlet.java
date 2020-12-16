package controller.frontend.shoppingcart;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.shoppingcart.ShoppingCart;

@WebServlet("/update_cart")
public class UpdateCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] arrayItemIds =  request.getParameterValues("itemId");
		
		int totalItems = arrayItemIds.length;
		String[] arrayQuantities = new String[totalItems];
		
		for (int i = 1; i <= totalItems; i++) {
			String quantity = request.getParameter("quantity" + i);
			arrayQuantities[i-1] = quantity;
		}
		
		int[]  itemIds = Arrays.stream(arrayItemIds).mapToInt(Integer::parseInt).toArray();
		int[] quantities = Arrays.stream(arrayQuantities).mapToInt(Integer::parseInt).toArray();
	
		ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
		cart.updateCart(itemIds, quantities);
		
		String cartPage = request.getContextPath().concat("/view_cart");
		response.sendRedirect(cartPage);
	}

}
