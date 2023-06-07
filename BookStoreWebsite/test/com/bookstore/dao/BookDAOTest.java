package com.bookstore.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import java.text.ParseException;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookDAOTest extends BaseDAOTest {
	private static BookDAO bookDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		BaseDAOTest.setUpBeforeClass();
		bookDAO = new BookDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		BaseDAOTest.tearDownAfterClass();
	}

	@Test
	public void testCreateBook() throws IOException, ParseException {
		Book newBook = new Book();

		Category category = new Category("Advanced Java");
		category.setCategoryId(1);
		newBook.setCategory(category);

		newBook.setTitle("Effective Java (2nd Edition)");
		newBook.setAuthor("Joshua Block");
		newBook.setDescription(
				"Are you looking for a deeper understanding of the Java™ programming language so that you can write");
		newBook.setPrice(38.87f);
		newBook.setIsbn("0321356683");

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("05/28/2008");
		newBook.setPublishDate((java.sql.Date) publishDate);

		String imagePath = "D:\\OneDrive - edy\\F\\Programming\\resourseforbookstorewebsiteudemy\\books\\Effective Java.JPG";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		newBook.setImage(imageBytes);

		Book createBook = bookDAO.create(newBook);

		assertTrue(createBook.getBookId() > 0);
	}

	@Test
	public void testCreate2ndBook() throws IOException, ParseException {
		Book newBook = new Book();

		Category category = new Category("Java Code");
		category.setCategoryId(2);
		newBook.setCategory(category);

		newBook.setTitle("Java 8 in Action");
		newBook.setAuthor("Alan Mycroft");
		newBook.setDescription("Java 8 in Action is a clearly written guide to the new features of Java 8.");
		newBook.setPrice(36.72f);
		newBook.setIsbn("1617291994");

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("08/28/2014");
		newBook.setPublishDate((java.sql.Date) publishDate);

		String imagePath = "D:\\OneDrive - edy\\F\\Programming\\resourseforbookstorewebsiteudemy\\books\\Java 8 in Action.JPG";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		newBook.setImage(imageBytes);

		Book createBook = bookDAO.create(newBook);

		assertTrue(createBook.getBookId() > 0);
	}

	@Test
	public void testUpdateBook() throws IOException, ParseException {
		Book existBook = new Book();
		existBook.setBookId(4);
		Category category = new Category("Java Core");
		category.setCategoryId(2);
		existBook.setCategory(category);

		existBook.setTitle("Effective Java (3rd Edition)");
		existBook.setAuthor("Joshua Block");
		existBook.setDescription(
				"Are you looking for a deeper understanding of the Java™ programming language so that you can write");
		existBook.setPrice(40f);
		existBook.setIsbn("0321356683");

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("05/28/2008");
		existBook.setPublishDate((java.sql.Date) publishDate);

		String imagePath = "D:\\OneDrive - edy\\F\\Programming\\resourseforbookstorewebsiteudemy\\books\\Effective Java.JPG";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		existBook.setImage(imageBytes);

		Book updatedBook = bookDAO.update(existBook);

		assertEquals(updatedBook.getTitle(), "Effective Java (3rd Edition)");
	}

	@Test
	public void testDeleteBookFail() {
		Integer bookId = 100;// assum didnt exists this id to get error in junit!!
		bookDAO.delete(bookId);

		assertTrue(true);

		/*
		 * javax.persistence.EntityNotFoundException: Unable to find
		 * com.bookstore.entity.Book with id 100
		 */

	}

	@Test(expected = EntityNotFoundException.class)
	public void testDeleteBookFailwithExceptionHandle() {
		Integer bookId = 100;// assum didnt exists this id to get error!!
		bookDAO.delete(bookId);

		assertTrue(true);

	}

	@Test
	public void testDeleteBookSuccess() {
		Integer bookId = 5;
		bookDAO.delete(bookId);

		assertTrue(true);

	}

	@Test
	public void testGetBookFail() {
		// we cant use expected "(expected=EntityNotFoundException.class)" bc this
		// method return null!!
		Integer bookId = 99;
		Book book = bookDAO.get(bookId);
		assertNull(book);
	}

	@Test
	public void testGetBookSuccess() {
		// we cant use expected "(expected=EntityNotFoundException.class)" bc this
		// method return null!!
		Integer bookId = 7;
		Book book = bookDAO.get(bookId);
		assertNotNull(book);
	}

	@Test
	public void testListAll() {
		List<Book> listBooks = bookDAO.listAll();
		for (Book book : listBooks) {
			System.out.println(book.getTitle() + " - " + book.getAuthor());
		}
		assertFalse(listBooks.isEmpty());
	}

	@Test
	public void testFindByTitleNotExist() {
		String title = "Thinking in Java";
		Book book = bookDAO.findByTitle(title);
		assertNull(book);
	}

	@Test
	public void testFindByTitleExist() {
		String title = "Java 8 in Action";
		Book book = bookDAO.findByTitle(title);
		assertNotNull(book);
	}

	@Test
	public void testCount() {
		long totalBooks = bookDAO.count();
		assertEquals(2, totalBooks);
	}
}
