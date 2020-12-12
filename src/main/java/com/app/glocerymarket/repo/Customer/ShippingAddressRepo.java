package com.app.glocerymarket.repo.Customer;

import java.util.List;
import java.util.Map;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.app.glocerymarket.entity.customer.ShippingAddressEntity;

public interface ShippingAddressRepo extends CrudRepository<ShippingAddressEntity, Long> {

	List<ShippingAddressEntity> findByCustomerId(Long customerId);

	@Query(vaule="")
	ShippingAddressEntity findbyDate(Long customerId);

}
