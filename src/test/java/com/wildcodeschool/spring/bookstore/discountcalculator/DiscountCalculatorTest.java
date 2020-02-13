package com.wildcodeschool.spring.bookstore.discountcalculator;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class DiscountCalculatorTest {

	@Test
	void shouldGiveNoDiscountWhenOneBookIsBought() {
		DiscountCalculator underTest = new DiscountCalculator();
		underTest.addBookWithPrice(5);
		assertThat(underTest.getSum()).isEqualTo(5);
	}

	@Test
	void shouldGiveTwoEuroDiscountWhenTwoBooksAreBought() {
		DiscountCalculator underTest = new DiscountCalculator();
		underTest.addBookWithPrice(5);
		underTest.addBookWithPrice(7);
		assertThat(underTest.getSum()).isEqualTo(10);
	}

}
