package com.app.glocerymarket.repo.Customer;

import java.util.List;
import java.util.Map;

import org.springframework.data.repository.CrudRepository;

import com.app.glocerymarket.entity.customer.PaymentDetailEntity;

public interface PaymentDetailRepo extends CrudRepository<PaymentDetailEntity, Long> {

	List<PaymentDetailEntity> findByCustomerId(Long customerId);

	PaymentDetailEntity findByDate(Long customerId);

}
