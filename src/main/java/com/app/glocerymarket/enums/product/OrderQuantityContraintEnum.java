package com.app.glocerymarket.enums.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderQuantityContraintEnum {
	QUANTITY(50);
	
	int quantity;
	
	public boolean hasLesserQuantity(int quantity) {
		return quantity < this.quantity;
	}
	
	public boolean isQuantityZero(int quantity) {
		return quantity<=0;
	}
}
