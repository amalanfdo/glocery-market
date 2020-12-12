package com.app.glocerymarket.entity.product;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Convert;
import javax.persistence.Entity;

import com.app.glocerymarket.enums.customer.InsertTypeEnum;

import lombok.Data;

@Entity
@Data
public class StockHistoryEntity {
	int stock_id;
	int product_id;
	int stock;
	@Convert(converter = InsertTypeEnum.class)
	InsertTypeEnum stock_update_type = InsertTypeEnum.ONLINE_TYPE;
	String inputUserId;
	Timestamp inputDateTime = Timestamp.valueOf(LocalDateTime.now());;
}
