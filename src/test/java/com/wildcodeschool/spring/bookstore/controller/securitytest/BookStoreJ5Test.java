package com.wildcodeschool.spring.bookstore.controller.securitytest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.wildcodeschool.spring.bookstore.controller.BookController;
import com.wildcodeschool.spring.bookstore.entity.Author;
import com.wildcodeschool.spring.bookstore.entity.Book;
import com.wildcodeschool.spring.bookstore.entity.Publisher;
import com.wildcodeschool.spring.bookstore.repository.AuthorRepository;
import com.wildcodeschool.spring.bookstore.repository.BookRepository;
import com.wildcodeschool.spring.bookstore.repository.PublisherRepository;

@SpringBootTest
@Transactional
@Disabled
class BookStoreJ5Test {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private PublisherRepository publisherRepository;
	
	@Autowired
	private AuthorRepository authorRepository;

	private MockMvc mockMvc;
	
	@BeforeEach
	void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(new BookController(bookRepository, publisherRepository, authorRepository)).build();
	}

	@Test
	void shouldGetAllBooks() throws Exception {
		
		bookRepository.save(getSimpleBook("Some interesting title"));
		
		ResultActions mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/books"));
		
		mvcResult.andExpect(MockMvcResultMatchers.status().is(200)).andExpect(MockMvcResultMatchers.model().attributeExists("books"));
		mvcResult.andDo(MockMvcResultHandlers.print());
		List<Book> books = (List<Book>) mvcResult.andReturn().getModelAndView().getModelMap().getAttribute("books");
		assertThat(books).size().isEqualTo(1);
		
	}

	@Test
	void shouldGetAllBooksAgain() throws Exception {
		
		bookRepository.save(getSimpleBook("Another interesting title"));
		
		ResultActions mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/books"));
		
		mvcResult.andExpect(MockMvcResultMatchers.status().is(200)).andExpect(MockMvcResultMatchers.model().attributeExists("books"));
		mvcResult.andDo(MockMvcResultHandlers.print());
		List<Book> books = (List<Book>) mvcResult.andReturn().getModelAndView().getModelMap().getAttribute("books");
		assertThat(books).size().isEqualTo(1);
		
	}

	private Book getSimpleBook(String title) {
		Book book = new Book();
		book.setTitle(title);
		book.setIsbn13("908081021878");
		
		Author author = new Author();
		author.setFirstName("Some");
		author.setLastName("Author");
		
		ArrayList<Author> authorList = new ArrayList<>();
		authorList.add(author);
		book.setAuthors(authorList);
		
		Publisher publisher = new Publisher();
		publisher.setName("Famous Publisher");
		book.setPublisher(publisher);
		
		publisherRepository.save(publisher);
		authorRepository.save(author);
		return book;
	}

	@Test
	void shouldInsertNewBook() throws Exception {
		Book bookWithoutCover = getSimpleBook("My First Upload");
		
		ResultActions mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/book/upsert")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .flashAttr("book", bookWithoutCover));
		
		mvcResult.andExpect(MockMvcResultMatchers.status().is(302));

		List<Book> allBooks = bookRepository.findAll();
		assertThat(allBooks).size().isEqualTo(1);
		assertThat(allBooks.get(0).getTitle()).isEqualTo("My First Upload");
	}
	
	

}
