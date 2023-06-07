package com.bookstore.controller.admin.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.controller.BaseServlet;
import com.bookstore.entity.Users;
import com.bookstore.service.UserServices;

@WebServlet("/admin/list_users")
public class ListUsersServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public ListUsersServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserServices userServices = new UserServices(request, response);
		userServices.listuser();

	}

}
