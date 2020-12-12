package com.app.glocerymarket.enums.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum StockUpdationTypeEnum {
	UPDATION(0),
	DELETION(1);
	@Getter
	int type;
}
