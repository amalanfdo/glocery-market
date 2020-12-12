package com.app.glocerymarket.service.serviceimpl.customer;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.glocerymarket.entity.customer.CustomerEntity;
import com.app.glocerymarket.entity.customer.OrderDetailEntity;
import com.app.glocerymarket.entity.customer.PaymentDetailEntity;
import com.app.glocerymarket.entity.customer.ShippingAddressEntity;
import com.app.glocerymarket.entity.responseentity.OrderHistoryResponse;
import com.app.glocerymarket.repo.Customer.CustomerRepo;
import com.app.glocerymarket.repo.Customer.OrderDetailRepo;
import com.app.glocerymarket.repo.Customer.PaymentDetailRepo;
import com.app.glocerymarket.repo.Customer.ShippingAddressRepo;
import com.app.glocerymarket.service.customer.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private ShippingAddressRepo shippingAddressRepo;
	
	@Autowired
	private PaymentDetailRepo paymentDetailRepo;
	
	@Autowired
	private OrderDetailRepo orderDetailRepo;
	
	
	public Optional<CustomerEntity> getCustomerById(Long customerId) {
		return customerRepo.findById(customerId);
	}

	@Override
	public ShippingAddressEntity getLastestAddress(Long customerId) {
		return shippingAddressRepo.findbyDate(customerId);
	}

	@Override
	public PaymentDetailEntity getLastestPayment(Long customerId) {
		return paymentDetailRepo.findByDate(customerId);
	}

	// implemented as a pagable it would be better implementation.
	@Override
	public List<OrderHistoryResponse> getPurchaseHistory(Long customerId) {
		// implementation can be change alterative way but it will be complex i think
		List<OrderDetailEntity> orderdetailList = orderDetailRepo.findByCustomerId(customerId);
		Map<Long,ShippingAddressEntity> shipmentMap = shippingAddressRepo
				.findByCustomerId(customerId).stream()
				.collect(Collectors.toMap(shipppingAddress -> shipppingAddress.getShippingAddressId(), shipppingAddress-> shipppingAddress));
		Map<Long,PaymentDetailEntity> paymentDetailMap = paymentDetailRepo
														.findByCustomerId(customerId)
														.stream()
														.collect(Collectors.toMap(paymentDetail -> paymentDetail.getPaymentDetailId(), paymentDetail-> paymentDetail));
		List<Long> orderId = orderdetailList.stream().map(orderDetail -> 
			orderDetail.getOrderDetailId()
		).collect(Collectors.toList());
		// logic need to continued due to time contrainst..
		return null;
	}

}
