package com.app.glocerymarket.entity.requestentity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductOrderRequest {
	private int userId;
	private int orderId;
	private int productId;
	private int quantity;
	private int price;
	
	public static boolean isValidParam(ProductOrderRequest productOrderRequest) {
		if(productOrderRequest.getUserId()<=0 || productOrderRequest.getOrderId()<=0 || productOrderRequest.getProductId()<=0)
			return false;
		return true;
	}
}
