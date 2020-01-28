package com.wildcodeschool.spring.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wildcodeschool.spring.bookstore.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
	// implementation happens automagically by Spring!
}
