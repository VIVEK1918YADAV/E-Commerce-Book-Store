package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Category;

public class CategoryDAOTest extends BaseDAOTest {

	private static CategoryDAO categoryDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		BaseDAOTest.setUpBeforeClass();
		categoryDao = new CategoryDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		BaseDAOTest.tearDownAfterClass();
	}

	@Test
	public void testCreateCategory() {
		Category newcat = new Category("Health");
		Category category = categoryDao.create(newcat);

		assertTrue(category != null && category.getCategoryId() > 0);
	}

	@Test
	public void testUpdateCategory() {
		Category category = new Category("java core 15");
		category.setCategoryId(1);
		Category category1 = categoryDao.update(category);
		assertEquals(category.getName(), category1.getName());

	}

	@Test
	public void testGet() {
		Integer catId = 2;
		Category cat = categoryDao.get(catId);
		// System.out.println(cat.getName());
		assertNotNull(cat);
	}

	@Test
	public void testDeleteCategory() {
		Integer catId = 5;
		categoryDao.delete(catId);
		Category cat = categoryDao.get(catId);
		assertNull(cat);
	}

	@Test
	public void testListAll() {
		List<Category> listCategories = categoryDao.listAll();
		for (Category category : listCategories) {
			System.out.println(category.getName());
		}
		assertTrue(listCategories.size() > 0);
	}

	@Test
	public void testCount() {
		long totalCategories = categoryDao.count();
		assertEquals(4, totalCategories);
	}

	@Test
	public void testFindByName() {
		String name = "JAVA";
		Category category = categoryDao.findByName(name);
		assertNotNull(category);

	}

	@Test
	public void testFindByNameNotFound() {
		String name = "JAVA core";
		Category category = categoryDao.findByName(name);
		assertNull(category);

	}

}
