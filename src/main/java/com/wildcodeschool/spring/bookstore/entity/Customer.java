package com.wildcodeschool.spring.bookstore.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.wildcodeschool.spring.bookstore.entity.user.User;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer extends User {

	private static final long serialVersionUID = 402247109185423508L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String firstName;

	private String lastName;

	private String address;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
	private List<Review> reviews;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
	private List<Order> orders;

	public Customer() {
		super.setRole("CUSTOMER");
	}

	@Override
	public void setRole(String role) {
		if (!"CUSTOMER".equals(role)) {
			throw new IllegalArgumentException();
		}
	}
}
