package com.app.glocerymarket.entity.customer;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Convert;
import javax.persistence.Entity;

import com.app.glocerymarket.enums.customer.UserStatusEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * CustomerEntity class is to maintain customer details.
 * 
 * @author amalante
 * @since 20.07
 */
@Entity
@Data
@NoArgsConstructor
public class CustomerEntity {
	private long userId;
	private String mailId;
	@Convert(converter = UserStatusEnum.class)
	private UserStatusEnum userStatus = UserStatusEnum.PERMENANT_ACCOUNT;
	private boolean isAdminUser =false;
	//private Integer age;
	//private Date dateOfBirth;
	private String inputUserId;
	private Timestamp inputDateTime =Timestamp.valueOf(LocalDateTime.now());;
	private String lastUpdateUserId;
	private Timestamp lastUpdateDateTime=Timestamp.valueOf(LocalDateTime.now());;
}
