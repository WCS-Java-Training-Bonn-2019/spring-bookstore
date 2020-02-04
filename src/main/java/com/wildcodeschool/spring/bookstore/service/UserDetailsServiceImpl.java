package com.wildcodeschool.spring.bookstore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wildcodeschool.spring.bookstore.entity.Customer;
import com.wildcodeschool.spring.bookstore.entity.user.AdminUserDetails;
import com.wildcodeschool.spring.bookstore.repository.CustomerRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final CustomerRepository repository;
	private final String adminPassword;

	@Autowired
	public UserDetailsServiceImpl(CustomerRepository repository, @Value("${admin.password}") String adminPassword) {
		this.repository = repository;
		this.adminPassword = adminPassword;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if ("admin".equals(username)) {
			return getAdminUserDetails();
		}
		return loadCustomerByName(username);
	}

	private UserDetails getAdminUserDetails() {
		return new AdminUserDetails("admin", adminPassword);
	}

	private UserDetails loadCustomerByName(String username) {
		Optional<Customer> optionalUser = repository.findByUsername(username);
		if (optionalUser.isPresent()) {
			return optionalUser.get();
		}
		throw new UsernameNotFoundException("User '" + username + "' not found.");
	}
}
