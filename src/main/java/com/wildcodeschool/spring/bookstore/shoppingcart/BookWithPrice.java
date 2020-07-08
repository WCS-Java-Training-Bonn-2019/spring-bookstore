package com.wildcodeschool.spring.bookstore.shoppingcart;

public class BookWithPrice {
	
	private String title;
	private String author;
	private int price;
	
	public BookWithPrice(String title, String author, int price) {
		super();
		this.title = title;
		this.author = author;
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public int getPrice() {
		return price;
	}

}
