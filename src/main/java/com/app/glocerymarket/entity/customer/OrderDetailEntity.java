package com.app.glocerymarket.entity.customer;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Convert;
import javax.persistence.Entity;

import org.hamcrest.collection.IsEmptyCollection;

import com.app.glocerymarket.enums.customer.InsertTypeEnum;
import com.app.glocerymarket.enums.customer.OrderStatusEnum;
import com.app.glocerymarket.utils.EmptyValidationUtil;

import lombok.Data;

@Entity
@Data
public class OrderDetailEntity {
	private long orderDetailId;
	private String invoiceNumber="";
	private long userId;
	@Convert(converter = OrderStatusEnum.class)
	private OrderStatusEnum orderStatusEnum = OrderStatusEnum.PENDING; 
	private long paymentDetailId;
	private long ShippingAddressId;
	private Date ShippingDate;
	private String feedback;
	@Convert(converter = InsertTypeEnum.class)
	private InsertTypeEnum orderType = InsertTypeEnum.ONLINE_TYPE;
	private String inputUserId;
	private Timestamp inputDateTime = Timestamp.valueOf(LocalDateTime.now());
	private String lastUpdateUserId;
	private Timestamp lastUpdateDateTime = Timestamp.valueOf(LocalDateTime.now());
	
	public static OrderDetailEntity formEntity(long userId) {
		if(EmptyValidationUtil.isEmpty(userId)) {
			return null;
		}
		return new OrderDetailEntity();
	}
}
