package com.wildcodeschool.spring.bookstore;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.wildcodeschool.spring.bookstore.converter.MultipartFileToByteArrayConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	public void addFormatters(FormatterRegistry formatterRegistry) {
		formatterRegistry.addConverter(new MultipartFileToByteArrayConverter());
	}

}
