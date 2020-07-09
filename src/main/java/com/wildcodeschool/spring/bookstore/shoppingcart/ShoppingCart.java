package com.wildcodeschool.spring.bookstore.shoppingcart;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.ListUtils;


public class ShoppingCart {

	List<BookWithPrice> books = new ArrayList<>();
	List<BookWithPrice> booksOfOneAuthor = new ArrayList<>();
	private double price;
	
	public double calculatePrice() {
		
		booksOfOneAuthor = filterBooksFromOneAuthor();
		// Buecher des einen Autors aus der Restmenge der Buecher herausnehmen
		
		books = ListUtils.subtract(books, booksOfOneAuthor);
		
		for (BookWithPrice bookWithPrice : books) {
			this.price += bookWithPrice.getPrice();
		}
		if(books.size() >= 3) {
			this.price = this.price * 0.95;
		}
		return price;
	}
	
	private List<BookWithPrice> filterBooksFromOneAuthor() {
		return new ArrayList<>();
	}

	public void addBook(BookWithPrice book) {
		books.add(book);
	}
	
}
