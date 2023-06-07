package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Category;

//test
public class CategoryServices {
	private EntityManager entityManager;

	private CategoryDAO categoryDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public CategoryServices(EntityManager entityManager, HttpServletRequest request, HttpServletResponse response) {

		this.request = request;
		this.response = response;
		this.entityManager = entityManager;
		categoryDAO = new CategoryDAO();
	}

	public void listCategory() throws ServletException, IOException {
		listCategory(null);
	}

	public void listCategory(String message) throws ServletException, IOException {
		List<Category> listCategory = categoryDAO.listAll();
		request.setAttribute("listCategory", listCategory);
		if (message != null) {
			request.setAttribute("message", message);
		}

		String listPage = "category_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);

	}

	public void createCategory() throws ServletException, IOException {
		String name = request.getParameter("name");
		Category existCategory = categoryDAO.findByName(name);
		if (existCategory != null) {
			String message = "could not create category." + "A category with name= " + name + " already exists.";
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		} else {
			Category newCategory = new Category(name);
			categoryDAO.create(newCategory);
			String message = "New category created successfully";
			listCategory(message);

		}
	}

	public void editCategory() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("id"));
		Category category = categoryDAO.get(categoryId);
		String editPage = "category_form.jsp";
		if (category != null) {
			request.setAttribute("category", category);
		}

		else {
			String message = "Could not find category with ID " + categoryId;
			request.setAttribute("message", message);
			editPage = "message.jsp";
		}

		RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
		requestDispatcher.forward(request, response);

	}

	public void updateCategory() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		String categoryName = request.getParameter("name");

		Category categoryById = categoryDAO.get(categoryId);
		Category categoryByName = categoryDAO.findByName(categoryName);

		if (categoryByName != null && categoryById.getCategoryId() != categoryByName.getCategoryId()) {
			String message = "could not update category." + " a category with name " + categoryName
					+ " already exists.";
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);

		} else {
			categoryById.setName(categoryName);
			categoryDAO.update(categoryById);
			String message = "Category has been updated successfully";
			listCategory(message);
		}

	}

	public void deleteCategory() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("id"));
		String message = "the category with ID " + categoryId + " has been removed successfully!";

		Category category = categoryDAO.get(categoryId);
		if (category == null) {
			message = "could not find category with id  " + categoryId + " it might have been deleted by "
					+ "another admin!";
			request.setAttribute("message", message);
			request.getRequestDispatcher("message.jsp").forward(request, response);
		}

		else {
			categoryDAO.delete(categoryId);
			listCategory(message);
		}
	}

}
