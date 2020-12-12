package com.app.glocerymarket.entity.responseentity;

import java.util.List;

import javax.persistence.Entity;

import com.app.glocerymarket.entity.customer.OrderDetailEntity;
import com.app.glocerymarket.entity.customer.PaymentDetailEntity;
import com.app.glocerymarket.entity.customer.ProductOrderEntity;
import com.app.glocerymarket.entity.customer.ShippingAddressEntity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderHistoryResponse {
	OrderDetailEntity orderDetailEntity;
	// we can create a new entity join with product table to get basic details.
	List<ProductOrderEntity> productOrder;
	ShippingAddressEntity shippingAddressEntity;
	PaymentDetailEntity paymentDetail;
}
