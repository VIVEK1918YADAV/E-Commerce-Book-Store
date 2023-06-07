package com.bookstore.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.bookstore.entity.Users;

public class CategoryTest {

	public static void main(String[] args) {
		Category newCat = new Category("Advanced Java");

		EntityManagerFactory entitymanagerfactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		EntityManager entityManager = entitymanagerfactory.createEntityManager();

		entityManager.getTransaction().begin();
		entityManager.persist(newCat);
		entityManager.getTransaction().commit();
		entityManager.close();
		entitymanagerfactory.close();

		System.out.println("a Category object was persisted");

	}

}
