package com.wildcodeschool.spring.bookstore.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wildcodeschool.spring.bookstore.entity.Book;
import com.wildcodeschool.spring.bookstore.repository.AuthorRepository;
import com.wildcodeschool.spring.bookstore.repository.BookRepository;
import com.wildcodeschool.spring.bookstore.repository.PublisherRepository;

@Controller
public class BookController {

	private static final int CLEANCODE_BOOK = 1;

	private final BookRepository repository;
	private final PublisherRepository publisherRepository;
	private final AuthorRepository authorRepository;

	@Autowired
	public BookController(BookRepository repository, PublisherRepository publisherRepository,
			AuthorRepository authorRepository) {
		this.repository = repository;
		this.publisherRepository = publisherRepository;
		this.authorRepository = authorRepository;
	}

	@GetMapping("/book/{bookId}/image")
	public ResponseEntity<byte[]> loadImage(@PathVariable Long bookId) {

		Optional<Book> optionalBook = repository.findById(bookId);
		if (optionalBook.isPresent() && optionalBook.get().getImage() != null) {
			Book book = optionalBook.get();
			return ResponseEntity.status(HttpStatus.OK)//
					.contentType(MediaType.IMAGE_JPEG)//
					.body(book.getImage());
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND)//
				.build();
	}

	@GetMapping("/books")
	public String getAll(Model model) {
		model.addAttribute("books", repository.findAll());
		return "book/get_all";
	}

	@GetMapping("/books/search")
	public String search(Principal principal, Model model, @RequestParam String searchString) {

		System.out.println("HUHU:" + principal.getName());

		model.addAttribute("books", repository
				.getByTitleContainingOrAuthorsLastNameContainingOrderByAvailableStockDesc(searchString, searchString));
		return "book/get_all";
	}

	@PostMapping("/book/upsert")
	public String insert(@Valid Book book, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("allPublishers", publisherRepository.findAll());
			model.addAttribute("allAuthors", authorRepository.findAll());
			return "book/edit";
		}			
		book = repository.save(book);
		return "redirect:/books";
	}

	@GetMapping({ "/book/new", "/book/edit/{id}" })
	public String edit(Model model, @PathVariable(required = false) Long id) {
		model.addAttribute("allPublishers", publisherRepository.findAll());
		model.addAttribute("allAuthors", authorRepository.findAll());
		if (id == null) {
			model.addAttribute("book", new Book());
			return "book/edit";
		}
		Optional<Book> optionalBook = repository.findById(id);
		if (optionalBook.isPresent()) {
			model.addAttribute("book", optionalBook.get());
		} else {
			return "redirect:/";
		}
		return "book/edit";
	}

	@GetMapping("/book/delete/{id}")
	public String delete(@PathVariable("id") long id) {
		repository.deleteById(id);
		return "redirect:/books";
	}

}
