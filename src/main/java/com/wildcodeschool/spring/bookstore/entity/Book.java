package com.wildcodeschool.spring.bookstore.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, name = "isbn13", insertable = true, updatable = true, unique = false)
	private String isbn13;

	@NotNull
	@Size(min = 2, max = 255, message = "Die Länge des Titels muss zwischen 2 und 255 Zeichen liegen.")
	private String title;

	private int availableStock;

	@ManyToOne
	@JoinColumn(name = "publisher_id")
	@NotNull(message = "Es muss ein Publisher ausgewählt werden.")
	private Publisher publisher;

	@ManyToMany
	@JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
	@NotEmpty(message = "Es muss mindestens ein Autor ausgewählt werden.")
	private List<Author> authors = new ArrayList<>();

	@Lob
	private byte[] image;

//	public void setPublisher(Publisher publisher) {
//		this.publisher = publisher;
//		if (publisher != null && !publisher.getBooks().contains(this)) {
//			publisher.getBooks().add(this);
//		}
//	}
//	
//	public void setAuthors(List<Author> authors) {
//		this.authors = authors;
//		for (Author author : authors) {
//			if (!author.getBooks().contains(this)) {
//				author.getBooks().add(this);
//			}			
//		}
//	}
}
