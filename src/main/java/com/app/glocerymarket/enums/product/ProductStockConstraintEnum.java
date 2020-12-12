package com.app.glocerymarket.enums.product;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ProductStockConstraintEnum {
	INVALID_PARAM(199),
	LIMIT_EXIST(200),
	STOCK_NOT_AVAILABLE(201),
	PRODUCT_NOT_EXIST(204),
	PRODUCT_UPDATED(300);

	@Getter
	@JsonValue
	int id;
}
