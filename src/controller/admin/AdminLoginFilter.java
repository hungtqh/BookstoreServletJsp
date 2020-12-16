package controller.admin;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter("/admin/*")
public class AdminLoginFilter implements Filter {

    public AdminLoginFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false); 
		
		boolean loggedIn = session != null && session.getAttribute("username") != null;
		String loginURI = httpRequest.getContextPath() + "/admin/login"; 
		boolean loginRequest = httpRequest.getRequestURI().equals(loginURI); 
		boolean loginPage = httpRequest.getRequestURI().endsWith("login.jsp");
		
		if (loggedIn && (loginPage || loginRequest)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin");
			dispatcher.forward(httpRequest, response);
		} else if (loggedIn || loginRequest) {
			chain.doFilter(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/login.jsp");
			dispatcher.forward(httpRequest, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
