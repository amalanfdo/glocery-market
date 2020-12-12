package com.app.glocerymarket.service.customer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.glocerymarket.entity.customer.CustomerEntity;
import com.app.glocerymarket.entity.customer.PaymentDetailEntity;
import com.app.glocerymarket.entity.customer.ShippingAddressEntity;
import com.app.glocerymarket.entity.responseentity.OrderHistoryResponse;
import com.app.glocerymarket.repo.Customer.CustomerRepo;
/**
 * CustomerService to maintain customer basic service
 * @author amalante
 *
 */

public interface CustomerService {

	Optional<CustomerEntity> getCustomerById(Long customerId);

	ShippingAddressEntity getLastestAddress(Long customerId);

	PaymentDetailEntity getLastestPayment(Long customerId);

	List<OrderHistoryResponse> getPurchaseHistory(Long customerId);

}
