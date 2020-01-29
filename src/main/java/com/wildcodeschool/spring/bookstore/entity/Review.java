package com.wildcodeschool.spring.bookstore.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Review {

	@EmbeddedId
	private ReviewId id;

	@ManyToOne
	@MapsId("bookId")
	private Book book;

	@ManyToOne
	@MapsId("customerId")
	private Customer customer;

	private String text;
	
	private int rating;
}
