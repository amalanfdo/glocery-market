package com.app.glocerymarket.enums.customer;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
public enum PaymentTypeEnum {
	ONLINE_PAYMENT(1),
	OFFLINE_PAYMENT(2);
	@Getter
	@JsonValue
	int payment_type;
	
}
