package com.app.glocerymarket.enums.customer;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
public enum UserStatusEnum {
	TEMPORARY_ACCOUNT(0),
	PERMENANT_ACCOUNT(1),
	ACCONT_LOCKED(2);
	
	@Getter
	@JsonValue
	int userStatus;
}
