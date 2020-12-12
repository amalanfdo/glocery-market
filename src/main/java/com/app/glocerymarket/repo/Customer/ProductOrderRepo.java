package com.app.glocerymarket.repo.Customer;

import org.springframework.data.repository.CrudRepository;

import com.app.glocerymarket.entity.customer.ProductOrderEntity;


public interface ProductOrderRepo extends CrudRepository<ProductOrderEntity, Long> {

	void deleteByIds(int orderId, int productId);

}
