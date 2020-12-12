package com.app.glocerymarket.entity.product;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class CategoryEntity {
	private long groceryId;
	private long categoryId;
	private String categoryName;
	private String inputUserId;
	private Timestamp inputDateTime = Timestamp.valueOf(LocalDateTime.now());
;
}
