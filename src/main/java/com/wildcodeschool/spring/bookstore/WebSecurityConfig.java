package com.wildcodeschool.spring.bookstore;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.wildcodeschool.spring.bookstore.service.UserDetailsServiceImpl;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private UserDetailsServiceImpl userDetailsService;
	
	public WebSecurityConfig (UserDetailsServiceImpl userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http
	        .authorizeRequests()
	            .antMatchers("/", "/images/**", "/styles.css", "/webjars/**").permitAll()
	            .antMatchers("/books", "/books/search").hasAnyRole("ADMIN", "CUSTOMER")
	            .anyRequest().hasRole("ADMIN")
	            .and()
	        .formLogin()
	            .and()
	        .httpBasic();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	
	public static void main(String[] args) {
		String encoded = new BCryptPasswordEncoder().encode("password");
		System.out.println(encoded);
	}
}
