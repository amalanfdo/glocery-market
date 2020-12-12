package com.app.glocerymarket.entity.customer;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class ProductOrderEntity {
	private long product_order_id;
	private long productId;
	private long orderId;
	private long orderQuantity;
	private long priceEach;
	private String inputUserId;
	private Timestamp inputDateTime = Timestamp.valueOf(LocalDateTime.now());
}
