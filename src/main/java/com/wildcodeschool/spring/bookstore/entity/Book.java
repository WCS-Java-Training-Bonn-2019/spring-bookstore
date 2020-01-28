package com.wildcodeschool.spring.bookstore.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, name = "isbn13", insertable = true, updatable = true, unique = false)
	private String isbn13;

	@NotNull
	@Size(min=2, max=255, message = "Die Länge des Titels muss zwischen 2 und 255 Zeichen liegen.")
	private String title;
	
	private int availableStock;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "publisher_id")
	@NotNull(message = "Es muss ein Publisher ausgewählt werden.")
	private Publisher publisher;

	@ManyToMany
	@JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
	@NotEmpty(message = "Es muss mindestens ein Autor ausgewählt werden.")
	private List<Author> authors = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public String getIsbn13() {
		return isbn13;
	}

	public String getTitle() {
		return title;
	}

	public int getAvailableStock() {
		return availableStock;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAvailableStock(int availableStock) {
		this.availableStock = availableStock;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

}
