package com.wildcodeschool.spring.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wildcodeschool.spring.bookstore.entity.Review;
import com.wildcodeschool.spring.bookstore.entity.ReviewId;

@Repository
public interface ReviewRepository extends JpaRepository<Review, ReviewId> {
	// implementation happens automagically by Spring!
}
