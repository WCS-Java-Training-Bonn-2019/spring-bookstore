package com.wildcodeschool.spring.bookstore.controller;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ImageController {

	private static final int CLEANCODE_BOOK = 1;

	@GetMapping("/image/{id}")
	public ResponseEntity<byte[]> loadImage(@PathVariable Long id) {
		
		try {
			
			byte[] imageAsByteArray;
			if(id == CLEANCODE_BOOK) {
				imageAsByteArray = loadCleanCodeImage();
			}else {
				imageAsByteArray = loadRefactoringImage();
			}
			return ResponseEntity.status(HttpStatus.OK)//
					.contentType(MediaType.IMAGE_JPEG)//
					.body(imageAsByteArray);

		} catch (IOException | NullPointerException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)//
					.build();
		}
	}

	private byte[] loadRefactoringImage() throws IOException {
		return IOUtils.toByteArray(this.getClass().getResourceAsStream("/static/images/Refactoring.jpeg"));
	}

	private byte[] loadCleanCodeImage() throws IOException {
		return IOUtils.toByteArray(this.getClass().getResourceAsStream("/static/images/CleanCodeCover.jpeg"));
	}
	
	
}
