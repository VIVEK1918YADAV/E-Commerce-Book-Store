package com.bookstore.controller.admin;

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

@WebFilter("/admin/*") // * :any request or response come i can filter it
public class AdminLoginFilter implements Filter {

	public AdminLoginFilter() {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// this code to confirm is already the user log in if yes mean let him access
		// all page
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);// get the session if the session exists

		boolean loggedIn = session != null && session.getAttribute("useremail") != null;
		// we add this if the user try to enter with correct email and pass so can
		// access to website
		String loginURI = httpRequest.getContextPath() + "/admin/login";
		boolean loginRequest = httpRequest.getRequestURI().equals(loginURI);
		boolean loginPage = httpRequest.getRequestURI().endsWith("login.jsp");
		if (loggedIn && (loginRequest || loginPage)) {
			// if you write page login and already loggedin so redirect you to main page
			// ,only must do logout
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/");
			dispatcher.forward(request, response);

		}

		else if (loggedIn || loginRequest) {

			chain.doFilter(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
		// with every request we show this :
		// System.out.println("AdminLoginFilter is invoked");

	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
