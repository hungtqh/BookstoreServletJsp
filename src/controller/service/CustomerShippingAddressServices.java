package controller.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.dao.CustomerShippingAddressDAO;
import model.customer.Customer;
import model.customer.CustomerShippingAddress;

public class CustomerShippingAddressServices {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private CustomerShippingAddressDAO customerShippingAddressDAO;

	public CustomerShippingAddressServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		customerShippingAddressDAO = new CustomerShippingAddressDAO();
	}

	public void listAllAddressByCustomer() throws ServletException, IOException {
		int customerId = Integer.parseInt(request.getParameter("id"));
		List<CustomerShippingAddress> listAddress = customerShippingAddressDAO.listAllByCustomer(customerId);

		request.setAttribute("listAddress", listAddress);
		String listPage = "shipping_address.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(listPage);
		dispatcher.forward(request, response);
	}

	public void listAllAddressByCustomer(int customerID) throws ServletException, IOException {
		List<CustomerShippingAddress> listAddress = customerShippingAddressDAO.listAllByCustomer(customerID);

		request.setAttribute("listAddress", listAddress);
		String listPage = "shipping_address.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(listPage);
		dispatcher.forward(request, response);
	}

	public void editAddress() throws ServletException, IOException {
		int addressId = Integer.parseInt(request.getParameter("id"));
		int customerId = Integer.parseInt(request.getParameter("customer_id"));

		CustomerShippingAddress address = customerShippingAddressDAO.get(addressId);

		if (address == null) {
			String msg = "Could not find customer address with ID = " + addressId;
			request.setAttribute("message", msg);
			listAllAddressByCustomer(customerId);
		} else {
			request.setAttribute("address", address);
			RequestDispatcher dispatcher = request.getRequestDispatcher("shipping_address_form.jsp");
			dispatcher.forward(request, response);
		}
	}

	public void updateCustomerShippingAddress() throws ServletException, IOException {
		int addressId = Integer.parseInt(request.getParameter("addressId"));
		int customerId = Integer.parseInt(request.getParameter("customerId"));
		CustomerShippingAddress address = customerShippingAddressDAO.get(addressId);

		if (address == null) {
			String msg = "Could not find customer address with ID = " + addressId;
			request.setAttribute("message", msg);
		} else {
			String recipientName = request.getParameter("recipientName");
			String recipientPhone = request.getParameter("recipientPhone");
			String city = request.getParameter("city");
			String detailAddress = request.getParameter("addressDetail");
			String province = request.getParameter("province");

			address.setAddressDetail(detailAddress);
			address.setCity(city);
			address.setProvince(province);
			address.setRecipientName(recipientName);
			address.setRecipientPhone(recipientPhone);

			customerShippingAddressDAO.update(address);

			String msg = "The shipping address has been updated successfully!";
			request.setAttribute("message", msg);
		}

		listAllAddressByCustomer(customerId);
	}

	public void deleteCustomerShippingAddress() throws ServletException, IOException {
		int addressId = Integer.parseInt(request.getParameter("id"));
		int customerId = Integer.parseInt(request.getParameter("customer_id"));
		CustomerShippingAddress address = customerShippingAddressDAO.get(addressId);

		if (address == null) {
			String msg = "Could not find customer address with ID = " + addressId + ".";
			request.setAttribute("message", msg);
		} else {
			customerShippingAddressDAO.delete(addressId);
			String msg = "The shipping address has been deleted successfully!";
			request.setAttribute("message", msg);
		}

		listAllAddressByCustomer(customerId);
	}

	public void showAllAddressByCustomer() throws ServletException, IOException {
		Customer customer = (Customer) request.getSession().getAttribute("loggedCustomer");
		int customerId = customer.getId();
		List<CustomerShippingAddress> listAddress = customerShippingAddressDAO.listAllByCustomer(customerId);

		request.setAttribute("listAddress", listAddress);
		String listPage = "frontend/shipping_address.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(listPage);
		dispatcher.forward(request, response);
	}

	public void createCustomerAddress() throws IOException, ServletException {
		String recipientName = request.getParameter("recipientName");
		String recipientPhone = request.getParameter("recipientPhone");
		String city = request.getParameter("city");
		String detailAddress = request.getParameter("addressDetail");
		String province = request.getParameter("province");

		CustomerShippingAddress address = new CustomerShippingAddress();
		address.setAddressDetail(detailAddress);
		address.setCity(city);
		address.setProvince(province);
		address.setRecipientName(recipientName);
		address.setRecipientPhone(recipientPhone);

		Customer customer = (Customer) request.getSession().getAttribute("loggedCustomer");
		address.setCustomer(customer);

		customerShippingAddressDAO.create(address);
		String msg = "The new shipping address has been created successfully!";
		request.setAttribute("message", msg);

		showAllAddressByCustomer();
	}

	public void editCustomerAddress() throws ServletException, IOException {
		int addressId = Integer.parseInt(request.getParameter("id"));

		CustomerShippingAddress address = customerShippingAddressDAO.get(addressId);

		if (address == null) {
			String msg = "Could not find address. The address does not exist!";
			request.setAttribute("message", msg);

			showAllAddressByCustomer();
		} else {
			request.setAttribute("address", address);
			RequestDispatcher dispatcher = request.getRequestDispatcher("frontend/shipping_address_form.jsp");
			dispatcher.forward(request, response);
		}
	}

	public void updateCustomerAddress() throws ServletException, IOException {
		int addressId = Integer.parseInt(request.getParameter("addressId"));
		CustomerShippingAddress address = customerShippingAddressDAO.get(addressId);

		if (address == null) {
			String msg = "Could not find address. The address does not exist!";
			request.setAttribute("message", msg);
		} else {
			String recipientName = request.getParameter("recipientName");
			String recipientPhone = request.getParameter("recipientPhone");
			String city = request.getParameter("city");
			String detailAddress = request.getParameter("addressDetail");
			String province = request.getParameter("province");

			address.setAddressDetail(detailAddress);
			address.setCity(city);
			address.setProvince(province);
			address.setRecipientName(recipientName);
			address.setRecipientPhone(recipientPhone);

			customerShippingAddressDAO.update(address);

			String msg = "The shipping address has been updated successfully!";
			request.setAttribute("message", msg);
		}

		showAllAddressByCustomer();
	}

	public void deleteCustomerAddress() throws ServletException, IOException {
		int addressId = Integer.parseInt(request.getParameter("id"));
		CustomerShippingAddress address = customerShippingAddressDAO.get(addressId);

		if (address == null) {
			String msg = "Could not find address. The address does not exist!";
			request.setAttribute("message", msg);
		} else {
			customerShippingAddressDAO.delete(addressId);
			String msg = "The shipping address has been deleted successfully!";
			request.setAttribute("message", msg);
		}

		showAllAddressByCustomer();
	}

}
