package com.wildcodeschool.spring.bookstore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wildcodeschool.spring.bookstore.entity.user.User;
import com.wildcodeschool.spring.bookstore.repository.user.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private UserRepository repository;

	@Autowired
	public UserDetailsServiceImpl(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optionalUser = repository.findByName(username);
		if (optionalUser.isPresent()) {
			return optionalUser.get();
		}
		throw new UsernameNotFoundException("User '" + username + "' not found.");
	}

}
