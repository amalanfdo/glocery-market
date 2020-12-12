package com.app.glocerymarket.enums.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
public enum OrderStatusEnum {
	PENDING(0),
	SUBMITTED(1),
	APPROVED(2),
	SHIPPING_INPROGRESS(3),
	DELIVERD(4),
	CANCEl_ORDER_REQUEST(5),
	CANCEL_APPROVED(6),
	CANCEL_REJECTED(7);
	@Getter
	int orderStatusEnum;
}
