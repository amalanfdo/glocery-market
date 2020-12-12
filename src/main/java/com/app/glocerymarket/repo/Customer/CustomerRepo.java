package com.app.glocerymarket.repo.Customer;

import org.springframework.data.repository.CrudRepository;

import com.app.glocerymarket.entity.customer.CustomerEntity;

public interface CustomerRepo extends CrudRepository<CustomerEntity, Long> {

}
