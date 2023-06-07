package com.bookstore.controller.admin.category;

import com.bookstore.controller.BaseServlet;
import com.bookstore.service.CategoryServices;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/edit_category")
public class EditCategortyServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public EditCategortyServlet() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CategoryServices categoryServices = new CategoryServices(entityManager, request, response);
		categoryServices.editCategory();

	}

}
