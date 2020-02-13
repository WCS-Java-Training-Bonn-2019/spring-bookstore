package com.wildcodeschool.spring.bookstore.controller.securitytest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.wildcodeschool.spring.bookstore.entity.Book;
import com.wildcodeschool.spring.bookstore.repository.AuthorRepository;
import com.wildcodeschool.spring.bookstore.repository.BookRepository;
import com.wildcodeschool.spring.bookstore.repository.PublisherRepository;

@SpringBootTest
@AutoConfigureMockMvc
class BookStoreJ5SecurityTest {

	@Autowired
	private MockMvc requestExecutor;
	

	@Autowired
	private BookRepository bookBepository;
	
	@Autowired
	private PublisherRepository publisherRepository;
	
	@Autowired
	private AuthorRepository authorRepository;

	@Test
	void shouldGetAllBooksAfterLogin() throws Exception {
		
		
		ResultActions mvcResult = requestExecutor.perform(MockMvcRequestBuilders.get("/books")
				.with(user("admjjhjin").password("admin2"))
				
				);
		
		mvcResult.andExpect(MockMvcResultMatchers.status().is(200)).andExpect(MockMvcResultMatchers.model().attributeExists("books"));
		mvcResult.andDo(MockMvcResultHandlers.print());
		List<Book> books = (List<Book>) mvcResult.andReturn().getModelAndView().getModelMap().getAttribute("books");
		assertThat(books).size().isEqualTo(0);
		
	}

}
