package com.wildcodeschool.spring.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wildcodeschool.spring.bookstore.entity.Author;

@SpringBootApplication
public class SpringBookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBookstoreApplication.class, args);
		
		Author author = new Author();
		author.setId(2L);
		author.setFirstName("Ralf");
		author.setLastName("Schopenhauer");
		System.out.println(author.toString());
	}

}
