package com.bookstore.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.bookstore.entity.Users;

public class UsersTest {

	public static void main(String[] args) {
		Users user1 = new Users();
		user1.setEmail("aa@a");
		user1.setFullName("ahmad salem abusafia");
		user1.setPassword("12345");

		EntityManagerFactory entitymanagerfactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		EntityManager entityManager = entitymanagerfactory.createEntityManager();

		entityManager.getTransaction().begin();
		entityManager.persist(user1);
		entityManager.getTransaction().commit();
		entityManager.close();
		entitymanagerfactory.close();

		System.out.println("a users object was persisted");

	}

}
