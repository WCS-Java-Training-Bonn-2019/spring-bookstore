package com.wildcodeschool.spring.bookstore.bootstrap;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.wildcodeschool.spring.bookstore.entity.user.User;
import com.wildcodeschool.spring.bookstore.repository.user.UserRepository;

@Component
public class BootstrapAdminUser implements CommandLineRunner {

	private final UserRepository userRepository;

	@Autowired
	public BootstrapAdminUser(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		Optional<User> optionalAdmin = userRepository.findByName("admin");
		if (!optionalAdmin.isPresent()) {
			System.out.println("***** No admin user found, adding admin/admin");
			User admin = new User();
			admin.setName("admin");
			admin.setPassword("$2y$12$yHMOyN3mqAyGYSXYrNCWsuVcAc7syXGZIrj/KwUzMcWTNjXG8IRei");
			admin.setRole("ADMIN");
			userRepository.save(admin);
		}
	}

}
