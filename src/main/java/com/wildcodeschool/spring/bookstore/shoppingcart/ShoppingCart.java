package com.wildcodeschool.spring.bookstore.shoppingcart;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

	List<BookWithPrice> books = new ArrayList<>();
	private double price;
	
	/**
	 * @deprecated Bitte nutzt ab sofort die Methode {{@link #calculatePrice()}
	 * @return
	 */
	public double getPrice() {
		return calculatePrice();
	}

	public double calculatePrice() {
		for (BookWithPrice bookWithPrice : books) {
			this.price += bookWithPrice.getPrice();
		}
		if(books.size() >= 3) {
			this.price = this.price * 0.95;
		}
		return price;
	}
	
	public void addBook(BookWithPrice book) {
		books.add(book);
	}
	
}
