package com.app.glocerymarket.service.product;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.glocerymarket.entity.product.CategoryEntity;
import com.app.glocerymarket.entity.product.ProductEntity;
import com.app.glocerymarket.entity.requestentity.ProductRequestFilter;

/**
 * ProductService api implementation.
 * @author amalante
 * @since 20.07
 * 
 */

public interface ProductService {
	/**
	 * getProductDetails method to handle product detail based on filter condition.
	 * @param productRequestFilter
	 * @return
	 */
	List<ProductEntity> getProductDetails(ProductRequestFilter productRequestFilter);

	/**
	 * getCategories to get category detail absed on 
	 * @param age
	 * @return
	 */
	List<CategoryEntity> getCategories(int age);

}
