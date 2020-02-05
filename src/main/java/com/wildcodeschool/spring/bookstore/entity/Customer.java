package com.wildcodeschool.spring.bookstore.entity;

import static java.util.Collections.singletonList;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer implements UserDetails {

	private static final long serialVersionUID = -836544528798493592L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String username;

	private String password;
	
	private String address;

	private String firstName;

	private String lastName;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
	private List<Order> orders;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
	private List<Review> reviews;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_CUSTOMER");
		return singletonList(authority);
	}

	@Override
	public String getUsername() {
		return username;
	}
		
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}	
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
