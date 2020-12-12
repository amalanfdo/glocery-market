package com.app.glocerymarket.enums.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum OrderStatusEnum {
	IN_PROGESS(0),
	ORDER_SUBMITTED(1),
	ORDER_APPROVED(2),
	SHIPPING(3),
	ORDER_SHIPPED(4),
	ORDER_RECEIVED(5);
	@Getter
	int orderStatus;
}
