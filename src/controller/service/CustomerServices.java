package controller.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.dao.AccountDAO;
import controller.dao.AddressDAO;
import controller.dao.CustomerDAO;
import controller.dao.CustomerShippingAddressDAO;
import controller.dao.FullNameDAO;
import controller.dao.PersonDAO;
import model.customer.Customer;
import model.customer.CustomerShippingAddress;
import model.person.Account;
import model.person.Address;
import model.person.FullName;
import model.person.Person;

public class CustomerServices {
	private CustomerDAO customerDAO;
	private AccountDAO accountDAO;
	private PersonDAO personDAO;
	private AddressDAO addressDAO;
	private FullNameDAO fullNameDAO;

	private HttpServletRequest request;
	private HttpServletResponse response;

	public CustomerServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		customerDAO = new CustomerDAO();
		accountDAO = new AccountDAO();
		personDAO = new PersonDAO();
		addressDAO = new AddressDAO();
		fullNameDAO = new FullNameDAO();
	}

	public void listCustomer() throws ServletException, IOException {
		// get all Customers from db
		List<Customer> customers = customerDAO.listAll();

		// set attribute for sending to jsp page
		request.setAttribute("listCustomer", customers);
		String listPage = "customer_list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(listPage);

		// send to jsp page
		dispatcher.forward(request, response);
	}

	public void createCustomer() throws ServletException, IOException {
		int accountID = Integer.parseInt(request.getParameter("account"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date dateOfBirth = null;
		try {
			dateOfBirth = sdf.parse(request.getParameter("dateOfBirth"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String detailAddress = request.getParameter("address");
		String city = request.getParameter("city");
		String province = request.getParameter("province");
		String gender = request.getParameter("gender");

		Account account = accountDAO.get(accountID);

		FullName fullname = new FullName(firstName, lastName);
		FullName createdFN = fullNameDAO.create(fullname);

		Address address = new Address(city, province, detailAddress);
		Address createdAddress = addressDAO.create(address);

		Person person = new Person(dateOfBirth, email, gender, phone);
		
		person.setAccount(account);
		person.setAddress(createdAddress);
		person.setFullName(createdFN);

		Person createdPerson = personDAO.create(person);

		Customer customer = new Customer();
		customer.setPerson(createdPerson);

		Customer createdCustomer = customerDAO.create(customer);

		if (createdCustomer.getId() > 0) { // success
			String msg = "Customer has been created successfully.";
			request.setAttribute("message", msg);
			listCustomer();
		}
	}

	public void showCustomerNewForm() throws ServletException, IOException {
		List<Account> listAccount = accountDAO.listAllUserAccountAvailable();
		request.setAttribute("listAccount", listAccount);

		String newPage = "customer_form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(newPage);
		dispatcher.forward(request, response);
	}

	public void editCustomer() throws ServletException, IOException {
		int customerID = Integer.parseInt(request.getParameter("id"));
		Customer findCustomer = customerDAO.get(customerID);

		if (findCustomer == null) {
			String msg = "Could not find customer with ID = " + customerID;
			request.setAttribute("message", msg);
			listCustomer();
		} else {
			request.setAttribute("customer", findCustomer);

			String editPage = "customer_form.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(editPage);
			dispatcher.forward(request, response);
		}
	}

	public void updateCustomer() throws ServletException, IOException {
		int customerID = Integer.parseInt(request.getParameter("customerID"));

		Customer findCustomer = customerDAO.get(customerID);

		if (findCustomer == null) {
			String msg = "Could not find customer with ID = " + customerID;
			request.setAttribute("message", msg);
			listCustomer();
			return;
		}

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date dateOfBirth = null;
		try {
			dateOfBirth = sdf.parse(request.getParameter("dateOfBirth"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String detailAddress = request.getParameter("address");
		String city = request.getParameter("city");
		String province = request.getParameter("province");
		String gender = request.getParameter("gender");

		Person person = findCustomer.getPerson();
		person.setDateOfBirth(dateOfBirth);
		person.setEmail(email);
		person.setPhone(phone);
		person.setGender(gender);

		Address address = person.getAddress();
		address.setCity(city);
		address.setProvince(province);
		address.setAddressDetail(detailAddress);

		FullName fullname = person.getFullName();
		fullname.setFirstName(firstName);
		fullname.setLastName(lastName);

		customerDAO.update(findCustomer);

		String msg = "Customer has been updated successfully.";
		request.setAttribute("message", msg);
		listCustomer();
	}

	public void deleteCustomer() throws ServletException, IOException {
		int customerID = Integer.parseInt(request.getParameter("id"));
		Customer findCustomer = customerDAO.get(customerID);

		if (findCustomer == null) {
			String msg = "Could not find customer with ID = " + customerID;
			request.setAttribute("message", msg);
			listCustomer();
			return;
		}
		
		if (findCustomer != null) {
			CustomerShippingAddressDAO shippingAddressDAO = new CustomerShippingAddressDAO();
			List<CustomerShippingAddress> addresses = shippingAddressDAO.listAllByCustomer(customerID);
			
			if (!addresses.isEmpty()) {
				String msg = "Could not delete customer. The customer still has some shipping addresses.";
				request.setAttribute("message", msg);
			} else {
				customerDAO.delete(customerID);
				String msg = "Customer has been deleted successfully.";
				request.setAttribute("message", msg);				
			}
			
			listCustomer();
		}
	}

	public void showLogin() throws ServletException, IOException {
		String loginPage = "frontend/login.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(loginPage);
		dispatcher.forward(request, response);
	}

	public void doLogin() throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		boolean isRight = accountDAO.checkLogin(username, password);
		Account account = accountDAO.findByUsername(username);
		if (isRight && account.getRole().equals("User")) {
			Person person = account.getPerson();
			Customer customer = person.getCustomer();
			HttpSession session = request.getSession(false);
			Object redirectURLObj = session.getAttribute("redirectURL");
			request.getSession().setAttribute("loggedCustomer", customer);

			if (redirectURLObj != null) {
				String redirectURI = (String) redirectURLObj;
				session.removeAttribute("redirectURL");
				response.sendRedirect(redirectURI);
			} else {
				showCustomerProfile();
			}

		} else {
			String msg = "Login failed. Please check username and password!";
			request.setAttribute("message", msg);
			showLogin();
		}
	}

	public void showCustomerProfile() throws ServletException, IOException {
		String profilePage = "frontend/customer_profile.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(profilePage);
		dispatcher.forward(request, response);

	}

	public void registerCustomer() throws ServletException, IOException {
		// get data form
		String username = request.getParameter("username");

		// find username and check if it already exists
		Account existAccount = accountDAO.findByUsername(username);

		if (existAccount != null) { // if the customer already exists
			String msg = "Could not register. The username " + username + " is used! <br/><br/>"
					+ "<a href=\"javascript:history.go(-1)\">Go Back</a>";
			request.setAttribute("message", msg);
		} else {
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Date dateOfBirth = null;
			try {
				dateOfBirth = sdf.parse(request.getParameter("dateOfBirth"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String detailAddress = request.getParameter("address");
			String city = request.getParameter("city");
			String province = request.getParameter("province");
			String gender = request.getParameter("gender");
			
			Account account = new Account(username, "User", password);
			Account createdAccount = accountDAO.create(account);
			
			FullName fullName = new FullName(firstName, lastName);
			FullName createdFN = fullNameDAO.create(fullName);

			Address address = new Address(city, province, detailAddress);
			Address createdAddress = addressDAO.create(address);

			Person person = new Person(dateOfBirth, email, gender, phone);
			person.setAccount(createdAccount);
			person.setAddress(createdAddress);
			person.setFullName(createdFN);

			Person createdPerson = personDAO.create(person);

			Customer customer = new Customer();
			customer.setPerson(createdPerson);

			customerDAO.create(customer);
			
			request.setAttribute("message",
					"Registered successfully! <br/><br/>" + "<a href='login'>Click here to login</a>");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("frontend/message.jsp");
		dispatcher.forward(request, response);
	}

	public void showCustomerProfileEditForm() throws ServletException, IOException {	
		String editPage = "frontend/edit_profile.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(editPage);
		dispatcher.forward(request, response);
	}

	public void updateCustomerProfile() throws ServletException, IOException {
		Customer customer = (Customer) request.getSession(false).getAttribute("loggedCustomer");

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date dateOfBirth = null;
		try {
			dateOfBirth = sdf.parse(request.getParameter("dateOfBirth"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String detailAddress = request.getParameter("addressDetail");
		String city = request.getParameter("city");
		String province = request.getParameter("province");
		String gender = request.getParameter("gender");
		
		String oldPassword = request.getParameter("oldPassword");
		String dbPassword = customer.getPerson().getAccount().getPassword();
		
		String newPassword = request.getParameter("password");
		
		if (oldPassword.length() > 0 && newPassword.length() > 0 && !oldPassword.equals(dbPassword)) {
			String msg = "Wrong password. Please try again!";
			request.setAttribute("message", msg);
			showCustomerProfileEditForm();
			
			return;
		}
		
		
		if (newPassword.length() > 0) {
			customer.getPerson().getAccount().setPassword(newPassword);
		}
		
		Person person = customer.getPerson();
		person.setDateOfBirth(dateOfBirth);
		person.setEmail(email);
		person.setPhone(phone);
		person.setGender(gender);
		
		FullName fullname = person.getFullName();
		fullname.setFirstName(firstName);
		fullname.setLastName(lastName);
		
		Address address = person.getAddress();
		address.setAddressDetail(detailAddress);
		address.setCity(city);
		address.setProvince(province);
		
		customerDAO.update(customer);

		String msg = "Updated profile successfully!";
		request.setAttribute("message", msg);
		showCustomerProfile();
	}

}
