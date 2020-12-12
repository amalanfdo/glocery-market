package com.app.glocerymarket.service.customer;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.glocerymarket.entity.common.InvoiceOrderDetail;
import com.app.glocerymarket.entity.common.StockViolatedEntity;
import com.app.glocerymarket.entity.requestentity.ProductOrderRequest;
/**
 * OrderDetailService maintain to order details .
 * @author amalante
 * @since 20.07
 */

public interface OrderDetailService {

	int getStock(long productId);

	int getlimit(long productId);

	int getExistOrder(long userId);

	int createOrder(long userId);

	void delete(ProductOrderRequest productOrderRequest);

	void insert(ProductOrderRequest productOrderRequest);

	int getTotalQuantity(long orderId);

	int getTotalAmount(long orderId);

	List<StockViolatedEntity> getStockViolatedDetail(long orderId);

	int getOrderCount(long userId);

	boolean isOrderExist(long orderId);

	Integer getProductOrderedCount(long orderId);

	List<InvoiceOrderDetail> getOrderResponse(long orderId);

}
