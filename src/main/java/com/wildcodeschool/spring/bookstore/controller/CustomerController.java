package com.wildcodeschool.spring.bookstore.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.wildcodeschool.spring.bookstore.entity.Customer;
import com.wildcodeschool.spring.bookstore.repository.CustomerRepository;

@Controller
public class CustomerController {

	private final CustomerRepository repository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public CustomerController(CustomerRepository repository, PasswordEncoder passwordEncoder) {
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
	}

	@GetMapping("/customers")
	public String getAll(Model model) {
		model.addAttribute("customers", repository.findAll());
		return "customer/get_all";
	}

	@PostMapping("/customer/upsert")
	public String insert(@ModelAttribute Customer customer) {
		if ("admin".equals(customer.getUsername())) {
			throw new IllegalArgumentException("admin not allowed here");
		}
		customer.setPassword(passwordEncoder.encode(customer.getPassword()));		
		customer = repository.save(customer);
		return "redirect:/customers";
	}

	@GetMapping({ "/customer/new", "/customer/edit/{id}" })
	public String edit(Model model, @PathVariable(required = false) Long id) {
		Customer customer = new Customer();
		if (id != null) {
			Optional<Customer> optionalCustomer = repository.findById(id);
			if (optionalCustomer.isPresent()) {
				customer = optionalCustomer.get();
			} else {
				return "redirect:/";
			}
		}
		model.addAttribute("customer", customer);
		return "customer/edit";
	}

	@GetMapping("/customer/delete/{id}")
	public String delete(@PathVariable("id") long id) {
		repository.deleteById(id);
		return "redirect:/customers";
	}

}
