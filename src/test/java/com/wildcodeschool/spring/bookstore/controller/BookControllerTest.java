package com.wildcodeschool.spring.bookstore.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.wildcodeschool.spring.bookstore.entity.Author;
import com.wildcodeschool.spring.bookstore.entity.Book;
import com.wildcodeschool.spring.bookstore.entity.Publisher;
import com.wildcodeschool.spring.bookstore.repository.AuthorRepository;
import com.wildcodeschool.spring.bookstore.repository.BookRepository;
import com.wildcodeschool.spring.bookstore.repository.PublisherRepository;


@SpringBootTest
@Transactional
class BookControllerTest {
	
	MockMvc mock;
	
	@Autowired
	BookRepository bookRepo;
	
	@Autowired
	AuthorRepository authorRepo;
	
	@Autowired
	PublisherRepository publisherRepo;
	
	@BeforeEach
	void setup() {
		BookController underTest = new BookController(bookRepo, publisherRepo, authorRepo);
		mock = MockMvcBuilders.standaloneSetup(underTest).build();
	}

	@Test
	void shouldReadAllBooks() throws Exception {
		//Given | Arrange
		
		
		//When | Act
		MvcResult result = mock.perform(MockMvcRequestBuilders.get("/books")).andReturn();
		//Then | Assert
		List<Book> books = getBooksFromModel(result);
		assertThat(books).hasSize(0);
	}
	
	@Test
	void shouldFindOneBook() throws Exception {
		//Given | Arrange
		givenABookInTheDatabase("A Simple Book");
		//When | Act
		MvcResult result = mock.perform(MockMvcRequestBuilders.get("/books")).andReturn();
		List<Book> books = getBooksFromModel(result);
		assertThat(books).hasSize(1);
	}

	@Test
	void shouldFindAnotherBook() throws Exception {
		//Given | Arrange
		givenABookInTheDatabase("A another Simple Book");
		//When | Act
		MvcResult result = mock.perform(MockMvcRequestBuilders.get("/books")).andReturn();
		//Then | Assert
		List<Book> books = getBooksFromModel(result);
		assertThat(books).hasSize(1);
	}

	@Test
	void shouldBeAbleToUploadABook() throws Exception {
		//Given | Arrange
		Book bookForUpload = getSimpleBook("Book for Upload");
		//When
		MvcResult result = mock.perform(MockMvcRequestBuilders.post("/book/upsert")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
	        .flashAttr("book", bookForUpload)).andReturn();
		//Then
		assertThat(result.getResponse().getStatus()).isEqualTo(302);
		List<Book> results = bookRepo.findAll(Example.of(bookForUpload));
		assertThat(results).hasSize(1);
		assertThat(results.get(0).getTitle()).isEqualTo("Book for Upload");
	}

	private List<Book> getBooksFromModel(MvcResult result) {
		ModelMap attributeMap = result.getModelAndView().getModelMap();
		List<Book> books = (List<Book>) attributeMap.get("books");
		return books;
	}

	private void givenABookInTheDatabase(String title) {
		Book book = getSimpleBook(title);
		bookRepo.save(book);
	}

	private Book getSimpleBook(String title) {
		Book book = new Book();
		book.setAvailableStock(1);
		book.setTitle(title);
		book.setIsbn13("1234567890123");
		
		Author author = new Author();
		author.setFirstName("John");
		author.setLastName("Doe");
		
		Publisher publisher = new Publisher();
		publisher.setName("Famous Publisher");
		
		authorRepo.save(author);
		publisherRepo.save(publisher);
		
		ArrayList<Author> authors = new ArrayList<>();
		authors.add(author);
		
		book.setAuthors(authors);
		book.setPublisher(publisher);
		return book;
	}

}
