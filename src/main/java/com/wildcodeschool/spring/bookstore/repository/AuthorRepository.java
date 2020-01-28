package com.wildcodeschool.spring.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wildcodeschool.spring.bookstore.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{
	// implementation happens automagically by Spring!
}
