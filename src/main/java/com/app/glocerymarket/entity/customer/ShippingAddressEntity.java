package com.app.glocerymarket.entity.customer;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * class to maintain Shipping details of user.
 * @author amalante
 * @since  20.07
 */
@Entity
@NoArgsConstructor
@Data
public class ShippingAddressEntity {
	long shippingAddressId;
	long userId;
	String area;
	String city;
	String state;
	String country;
	String postalCode;
	private String inputUserId;
	private Timestamp inputDateTime = Timestamp.valueOf(LocalDateTime.now());
}
