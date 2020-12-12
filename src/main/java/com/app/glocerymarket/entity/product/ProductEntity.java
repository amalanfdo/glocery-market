package com.app.glocerymarket.entity.product;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Convert;
import javax.persistence.Entity;

import com.app.glocerymarket.enums.customer.InsertTypeEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class ProductEntity {
	private long productId;
	private long categoryId;
	private String productName;
	private String productDescription;
	private String imageUrl;
	private float price;
	private int rating; // need to develop with some extension or update using batch processing.
	private long stock;
	private long stockLimit;
	@Convert(converter=InsertTypeEnum.class)
	private InsertTypeEnum productUpdateType = InsertTypeEnum.ONLINE_TYPE;
	private String inputUserId;
	private Timestamp inputDateTime;
	private String lastUpdateUserId;
	private Timestamp lastUpdateDateTime = Timestamp.valueOf(LocalDateTime.now());
}
