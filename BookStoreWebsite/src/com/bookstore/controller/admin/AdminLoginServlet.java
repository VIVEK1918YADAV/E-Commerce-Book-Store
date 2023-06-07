package com.bookstore.controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.controller.BaseServlet;
import com.bookstore.service.UserServices;

@WebServlet("/admin/login")
public class AdminLoginServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserServices userServices = new UserServices(request, response);
		userServices.login();
		/*
		 * to test only String email=request.getParameter("email"); String
		 * password=request.getParameter("password");
		 * response.getWriter().println(email+" , "+password);
		 */

	}

}
