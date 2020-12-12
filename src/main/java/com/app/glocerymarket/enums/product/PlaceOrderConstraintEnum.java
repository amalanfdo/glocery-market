package com.app.glocerymarket.enums.product;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum PlaceOrderConstraintEnum {
	QUANTITY_ZERO(80),
	QUANTITY_GEATER(81),
	AMOUNT_ZERO(90),
	AMOUNT_GREATER(91),
	NO_ERROR(100);
	@JsonValue
	@Getter
	int errorCode;
}
