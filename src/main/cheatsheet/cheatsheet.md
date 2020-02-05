# Live-Coding Spring Security

## Step 1: Basic security config

```java
package com.wildcodeschool.spring.bookstore;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http
	        .authorizeRequests()
	            .anyRequest().authenticated()
	            .and()
	        .formLogin()
	            .and()
	        .httpBasic();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("user").password("{noop}password").roles("USER");
	}
}
```

## Step 2: Configure unauthenticated access to landing page

```java
.antMatchers("/", "/images/**", "/styles.css", "/webjars/**").permitAll()
``` 

## Step 3: Connect MySQL

* User.java (implements UserDetails)
* UserRepository.java
* UserDetailsService.java
* WebSecurityConfig.java anpassen

## Step 4: BCrypt password encoding

* WebSecurityConfig.java anpassen
* [Bcrypt-Generator.com - Online Bcrypt Hash Generator & Checker](https://bcrypt-generator.com/)
* BootstrapAdminUser

## Step 5: Integrate Customer management

* WebSecurityConfig.java anpassen
* Customer.java anpassen (extends User)
* edit.html anpassen
* get_all.html anpassen

```java
    .antMatchers("/books", "/books/search").hasAnyRole("ADMIN", "CUSTOMER")
    .anyRequest().hasRole("ADMIN")
```

## Step 6: Alternative Behandlung des Admin-Users

* UserDetailsService anpassen (Property Injection)
* User anpassen

## Step 7: Thymeleaf: Login / Logout

* https://www.thymeleaf.org/doc/articles/springsecurity.html
* pom.xml: thymeleaf-extras-springsecurity5
* index.html anpassen
* sec:authorize="hasRole('ROLE_ADMIN')

```xml
        <dependency>
          <groupId>org.thymeleaf.extras</groupId>
          <artifactId>thymeleaf-extras-springsecurity5</artifactId>
        </dependency>
``` 

```html
      <ul class="nav navbar-nav navbar-right">
        <li class="nav-item">
          <a class="nav-link" href="#" th:href="@{/login}" sec:authorize="!isAuthenticated()">Login</a>
          <a class="nav-link" href="#" th:href="@{/logout}" sec:authorize="isAuthenticated()"><span sec:authentication="name"></span> (logout)</a>
        </li>
      </ul>
``` 

## Step 8: Principal im Controller zugreifen

## User

* Ada Lovelace, London
* Charles Babbage, London
* Alan Turing, Wilmslow