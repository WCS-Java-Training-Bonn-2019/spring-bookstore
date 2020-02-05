package com.wildcodeschool.spring.bookstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wildcodeschool.spring.bookstore.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

	// implementation happens automagically by Spring!
	Optional<Customer> findByUsername(String username);

}
