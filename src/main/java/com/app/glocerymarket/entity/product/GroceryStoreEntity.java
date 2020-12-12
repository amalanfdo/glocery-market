package com.app.glocerymarket.entity.product;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class GroceryStoreEntity {
	private long grocery_id;
	private String gstNumber;
	private String name;
	private String inputUserId;
	private Timestamp inputDateTime =Timestamp.valueOf(LocalDateTime.now());
}
