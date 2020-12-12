package com.app.glocerymarket.api.customer;

import static com.app.glocerymarket.utils.EmptyValidationUtil.isEmpty;
import static com.app.glocerymarket.utils.EmptyValidationUtil.moreThanOne;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.glocerymarket.entity.customer.CustomerEntity;
import com.app.glocerymarket.entity.customer.PaymentDetailEntity;
import com.app.glocerymarket.entity.customer.ShippingAddressEntity;
import com.app.glocerymarket.entity.responseentity.OrderHistoryResponse;
import com.app.glocerymarket.exception.customer.CustomerNotFoundException;
import com.app.glocerymarket.service.customer.CustomerService;
import com.app.glocerymarket.service.customer.OrderDetailService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController("store/customer")
@Api( tags="this customer basic opeartion")
public class CustomerController {

	@Autowired
	CustomerService customerSerivce;
	
	@Autowired
	OrderDetailService orderDetailService;
	
	@ApiOperation(value = "get single customer detail")
	@RequestMapping(value="/customer/{customerId}" , method=RequestMethod.GET)
	public ResponseEntity<CustomerEntity> getCustomerDetail(@PathVariable("customerId") long customerId) {
		if(isEmpty(customerId)) {
			 throw new CustomerNotFoundException();
		}
		Optional<CustomerEntity> customer = customerSerivce.getCustomerById(customerId); 
		if(isEmpty(customer)) {
			throw new CustomerNotFoundException();
		}
		return ResponseEntity.ok(customer.get());
	}
	
	@ApiOperation(value = "based on previous order we get preferrable Address")
	@RequestMapping(value="/get-preferrable-address" , method=RequestMethod.GET)
	public ResponseEntity<ShippingAddressEntity> getPreferrableAddress(@PathVariable("customerId") long customerId) {
		if(isEmpty(customerId)) {
			 throw new CustomerNotFoundException();
		}
		int orderCount = orderDetailService.getOrderCount(customerId);
		ShippingAddressEntity shippingAdress = null;
		if(moreThanOne(orderCount)) {
			shippingAdress = customerSerivce.getLastestAddress(customerId);
		}
		return ResponseEntity.ok(shippingAdress);
	}

	@ApiOperation(value = "based on previous order we get preferrable payment")
	@RequestMapping(value="/get-preferrable-payment" , method=RequestMethod.GET)
	public ResponseEntity<PaymentDetailEntity> getPreferrablePayment(@PathVariable("customerId") long customerId) {
		if(Objects.isNull(customerId <=0)) {
			 throw new CustomerNotFoundException();
		}
		int orderCount = orderDetailService.getOrderCount(customerId);
		PaymentDetailEntity paymentDetailEntity = null;
		if(moreThanOne(orderCount)) {
			paymentDetailEntity = customerSerivce.getLastestPayment(customerId);
		}
		return ResponseEntity.ok(paymentDetailEntity);
	}

	// implemented as a pagable it would be better implementation.
	@ApiOperation(value = "get Purchase History")
	@RequestMapping(value="/get-purchase-history" , method=RequestMethod.GET)
	public List<OrderHistoryResponse> getPurchaseHistory(@PathVariable("customerId") long customerId) {
		if(isEmpty(customerId)) { 
			 throw new CustomerNotFoundException();
		}
		return customerSerivce.getPurchaseHistory(customerId);
	}
		
}
