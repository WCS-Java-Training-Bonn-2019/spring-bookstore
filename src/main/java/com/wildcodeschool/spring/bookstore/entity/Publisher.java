package com.wildcodeschool.spring.bookstore.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Publisher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank (message = "A publisher must not be blank")
	@Size(min = 5, message = "A publisher must have at least 5 characters")
	private String name;

	@OneToMany(mappedBy = "publisher")
	private List<Book> books = new ArrayList<>();
}
