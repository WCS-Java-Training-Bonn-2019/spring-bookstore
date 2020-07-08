package com.wildcodeschool.spring.bookstore.shoppingcart;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class ShoppingCartTest {

	ShoppingCart underTest = new ShoppingCart();

	@Test
	void shouldReturnZeroEurosAsPrice_whenShoppingCartIsEmpty() {
		double price = underTest.calculatePrice();
		
		assertThat(price).isEqualTo(0);
	}
	
	@Test
	void shouldReturnExactPrice_whenShoppingCartHasOneItem() {
		underTest.addBook(new BookWithPrice("Weltatlas", "M. Autor", 10));
		
		double price = underTest.calculatePrice();
		
		assertThat(price).isEqualTo(10);
	}
	
	@Test
	void shouldReturnExactPrice_whenShoppingCartHasTwoItems() {
		underTest.addBook(new BookWithPrice("Weltatlas", "M. Autor", 10));
		underTest.addBook(new BookWithPrice("Geschichtbuch", "Anderen Autor", 12));
		
		double price = underTest.getPrice();
		
		assertThat(price).isEqualTo(22);
	}
	
	@Test
	void shouldReturnPriceWithFivePercentDiscount_whenShoppingCartHas3Items() {
		underTest.addBook(new BookWithPrice("Weltatlas", "M. Autor", 10));
		underTest.addBook(new BookWithPrice("Geschichtbuch", "Anderen Autor", 12));
		underTest.addBook(new BookWithPrice("Biologiebuch", "B. Iologie", 8));
		
		double price = underTest.calculatePrice();
		
		assertThat(price).isEqualTo(28.5);
	}	
	
	@Test
	void shouldReturnPriceWithFivePercentDiscount_whenShoppingCartHasMOreThan3Items() {
		underTest.addBook(new BookWithPrice("Weltatlas", "M. Autor", 10));
		underTest.addBook(new BookWithPrice("Geschichtbuch", "Anderen Autor", 12));
		underTest.addBook(new BookWithPrice("Biologiebuch", "B. Iologie", 8));
		underTest.addBook(new BookWithPrice("Biologiebuch", "B. Iologie", 8));
		
		double price = underTest.calculatePrice();
		
		assertThat(price).isEqualTo(36.1);
	}
}
