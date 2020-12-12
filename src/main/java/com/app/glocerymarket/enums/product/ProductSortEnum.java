package com.app.glocerymarket.enums.product;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public enum ProductSortEnum {
	NAME(1),
	LOWTOHIGH(2),
	HIGHTOLOW(3),
	ASCDATE(4);
	ProductSortEnum(int id) {
		this.id = id;
	}
	
	@JsonValue
	@Getter
	int id;
	
	public static Sort getSort(ProductSortEnum productSortEnum) {
		switch(productSortEnum) {
		case NAME:
			break;
		case LOWTOHIGH:
			break;
		case HIGHTOLOW:
		case ASCDATE:
		}
		return null;
	}
	
}
