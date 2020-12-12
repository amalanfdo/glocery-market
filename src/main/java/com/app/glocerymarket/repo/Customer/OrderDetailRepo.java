package com.app.glocerymarket.repo.Customer;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.app.glocerymarket.entity.customer.OrderDetailEntity;

public interface OrderDetailRepo extends CrudRepository<OrderDetailEntity, Long> {

	List<OrderDetailEntity> findByCustomerId(Long customerId);

	int getExistOrder(long userId);

}
