package controller.frontend.customershippingaddress;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.service.CustomerShippingAddressServices;

@WebServlet("/edit_address")
public class EditAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerShippingAddressServices services = new CustomerShippingAddressServices(request, response);
		services.editCustomerAddress();
	}

}
