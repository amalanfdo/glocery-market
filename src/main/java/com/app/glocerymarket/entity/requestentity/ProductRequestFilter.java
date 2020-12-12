package com.app.glocerymarket.entity.requestentity;


import com.app.glocerymarket.enums.product.ProductSortEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class ProductRequestFilter {
	int categoryId;
	String searchKeyword;
	ProductSortEnum productSort;
	int limit;
	int offset;
	
	public static boolean isValidParam(ProductRequestFilter productRequestFilter) {
		if(productRequestFilter.limit<=0 || productRequestFilter.offset<0 || productRequestFilter.categoryId==0) {
			return false;
		}
		return true;
	}
	
	public static ProductRequestFilter init() {
		return ProductRequestFilter.builder()
				.limit(0)
				.offset(10)
				.productSort(ProductSortEnum.NAME)
				.searchKeyword("")
				.build();
	}
	

}
