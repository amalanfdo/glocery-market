package com.app.glocerymarket.enums.product;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrderAmountConstraintEnum {
	AMOUNT(50000);
	int amount;

	public boolean hasGeaterAmount(int amount) {
		return amount > this.amount;
	}
}
