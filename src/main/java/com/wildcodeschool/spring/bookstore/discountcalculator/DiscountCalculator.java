package com.wildcodeschool.spring.bookstore.discountcalculator;

import java.util.ArrayList;
import java.util.List;

public class DiscountCalculator {

	private List<Integer> prices = new ArrayList<>();

	public void addBookWithPrice(int price) {
		this.prices.add(price);
	}

	public int getSum() {
		int sum = 0;
		for (Integer price : prices) {
			sum += price;
		}
		
		if(prices.size() == 2) {
			sum -= 2;
		}
		
		return sum;
	}

}
