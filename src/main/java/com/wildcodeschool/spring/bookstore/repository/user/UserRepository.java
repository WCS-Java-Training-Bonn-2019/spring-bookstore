package com.wildcodeschool.spring.bookstore.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wildcodeschool.spring.bookstore.entity.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByName(String name);
}
