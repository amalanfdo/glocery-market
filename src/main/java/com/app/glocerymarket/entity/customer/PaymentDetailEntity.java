package com.app.glocerymarket.entity.customer;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Convert;
import javax.persistence.Entity;

import com.app.glocerymarket.enums.customer.PaymentTypeEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class PaymentDetailEntity {
	long userId;
	long paymentDetailId;
	@Convert(converter= PaymentTypeEnum.class)
	PaymentTypeEnum paymentType = PaymentTypeEnum.OFFLINE_PAYMENT;
	String paymentNo;
	String inputUserId;
	Timestamp inputDatetime = Timestamp.valueOf(LocalDateTime.now());;
}
