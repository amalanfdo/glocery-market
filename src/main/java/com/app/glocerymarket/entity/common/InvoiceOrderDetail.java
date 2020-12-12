package com.app.glocerymarket.entity.common;

import javax.persistence.Entity;

import lombok.Data;
// mainly for checkout details , show purchase history
@Entity
@Data
public class InvoiceOrderDetail {
	private long productId;
	private String productName;
	private String productDescription;
	private long orderQuantity;
	private float priceEach;
	private float totalAmount;
	private String producImg;
}
