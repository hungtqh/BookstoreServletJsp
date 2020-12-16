package controller.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.dao.AccountDAO;
import model.person.Account;

public class AccountServices {
	private AccountDAO accountDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public AccountServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;

		accountDAO = new AccountDAO();
	}

	public void listAccount() throws ServletException, IOException {
		// get all Accounts from db
		List<Account> accounts = accountDAO.listAll();

		// set attribute for sending to jsp page
		request.setAttribute("accounts", accounts);
		String listPage = "user_list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(listPage);

		// send to jsp page
		dispatcher.forward(request, response);
	}

	public void createAccount() throws ServletException, IOException {
		// get data form
		String username = request.getParameter("username").trim();
		String role = request.getParameter("role").trim();
		String password = request.getParameter("password");

		// find account and check if it already exists
		Account existAccount = accountDAO.findByUsername(username);

		if (existAccount != null) { // if the Account already exists
			String msg = "Could not create Account with username " + username + " already exists";
			request.setAttribute("message", msg);

			RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
			dispatcher.forward(request, response);
		} else {
			// create new Account and save to db
			accountDAO.create(new Account(username, role, password));
			request.setAttribute("message", "New account was created successfully!");

			listAccount();
		}
	}

	public void editAccount() throws ServletException, IOException {
		int accountId = Integer.parseInt(request.getParameter("id"));
		Account account = accountDAO.get(accountId);

		// check if Account exists
		if (account == null) {
			String msg = "Could not find Account with ID = " + accountId;
			request.setAttribute("message", msg);

			RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
			dispatcher.forward(request, response);
		} else {
			String editPage = "user_form.jsp";
			request.setAttribute("account", account);

			RequestDispatcher dispatcher = request.getRequestDispatcher(editPage);
			dispatcher.forward(request, response);
		}
	}

	public void updateAccount() throws ServletException, IOException {
		String username = request.getParameter("username").trim();
		String role = request.getParameter("role").trim();
		String password = request.getParameter("password");
		int accountId = Integer.parseInt(request.getParameter("accountId"));

		Account accountByUsername = accountDAO.findByUsername(username);
		Account accountById = accountDAO.get(accountId);

		// check if Account exists
		if (accountById == null) {
			String msg = "Could not find Account with ID = " + accountId;
			request.setAttribute("message", msg);

			RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
			dispatcher.forward(request, response);
		} else if (accountByUsername != null && accountByUsername.getId() != accountId) {
			String msg = "Could not update Account. Account with username " + username + " already exists!";
			request.setAttribute("message", msg);

			RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
			dispatcher.forward(request, response);
		} else {
			Account newAccount = new Account(accountId, username, password, role);
			accountDAO.update(newAccount);

			String msg = "Account has been updated successfully";
			request.setAttribute("message", msg);

			listAccount();
		}
	}

	public void deleteAccount() throws ServletException, IOException {
		int accountId = Integer.parseInt(request.getParameter("id"));

		Account findAccount = accountDAO.find(Account.class, accountId);
		Account isUsedAccount = accountDAO.checkIsUsed(accountId);

		if (findAccount == null) {
			String msg = "Could not find Account with Id = " + accountId + ". Account doesn't exist!";
			request.setAttribute("message", msg);

		} 
		else if (isUsedAccount != null) {
			String msg = "Could not delete Account with Id = " + accountId + ". Account is used by a customer.";
			request.setAttribute("message", msg);

		}
		else if (findAccount != null && findAccount.getRole().equals("super")) {
			String msg = "Could not delete super Account!";
			request.setAttribute("message", msg);
		} else {
			accountDAO.delete(accountId);

			String msg = "Account has been deleted successfully!";
			request.setAttribute("message", msg);
		}

		listAccount();
	}

	public void login() throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		boolean loginResult = accountDAO.checkLogin(username, password);
		Account account = accountDAO.findByUsername(username);
		String role = "";
		if (account != null) {
			role = account.getRole();
		}
		if (loginResult && (role.equals("Admin"))) {
			request.getSession().setAttribute("username", username);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin");
			dispatcher.forward(request, response);
		} else {
			String msg = "Login failed!";
			request.setAttribute("message", msg);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/login.jsp");
			dispatcher.forward(request, response);
		}
	}
}
