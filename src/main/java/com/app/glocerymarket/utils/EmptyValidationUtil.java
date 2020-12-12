package com.app.glocerymarket.utils;

import java.util.Optional;

import com.app.glocerymarket.entity.customer.CustomerEntity;

public class EmptyValidationUtil {

	public static boolean isEmpty(int value) {
		return value <=0;
	}
	
	public static boolean isEmpty(long value) {
		return value <=0;
	}
	
	public static boolean isEmpty(String value) {
		return value != null && !value.equals("");
	}
	
	public static boolean moreThanOne(long value) {
		return value>=2;
	}
	
	public static boolean moreThanOne(int value) {
		return value>=2;
	}
	
	public static boolean isEmpty(Optional<CustomerEntity> customerOptionalEntity) {
		return !customerOptionalEntity.isPresent();
	}
	
	public static boolean isNotEmpty(int value) {
		return !isEmpty(value);
	}
	
	public static boolean isNotEmpty(long value) {
		return !isEmpty(value);
	}
	
	public static boolean isNotEmpty(String value) {
		return !isEmpty(value);
	}
}
