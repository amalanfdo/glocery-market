package com.app.glocerymarket.enums.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
public enum InsertTypeEnum {
	ONLINE_TYPE(0),
	BATCH_TYPE(1);
	@Getter
	int insertType;
}
