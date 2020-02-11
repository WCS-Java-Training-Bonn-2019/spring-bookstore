package com.wildcodeschool.spring.bookstore.converter;

import java.io.IOException;

import org.springframework.core.convert.converter.Converter;
import org.springframework.web.multipart.MultipartFile;

public class MultipartFileToByteArrayConverter implements Converter<MultipartFile, byte[]>{

	@Override
	public byte[] convert(MultipartFile source) {
		try {
			return source.getBytes();
		} catch (IOException e) {
			throw new RuntimeException (e.getMessage());
		}
	}

}
