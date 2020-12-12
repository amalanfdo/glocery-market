package com.app.glocerymarket.service.serviceimpl.customer;


import static com.app.glocerymarket.utils.EmptyValidationUtil.isEmpty;
import static com.app.glocerymarket.utils.EmptyValidationUtil.isNotEmpty;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.glocerymarket.entity.common.InvoiceOrderDetail;
import com.app.glocerymarket.entity.common.StockViolatedEntity;
import com.app.glocerymarket.entity.customer.OrderDetailEntity;
import com.app.glocerymarket.entity.requestentity.ProductOrderRequest;
import com.app.glocerymarket.repo.Customer.OrderDetailRepo;
import com.app.glocerymarket.repo.Customer.ProductOrderRepo;
import com.app.glocerymarket.repo.Product.ProductRepo;
import com.app.glocerymarket.service.customer.OrderDetailService;

public class OrderDetailServiceImpl implements OrderDetailService{

	@Autowired
	ProductRepo productRepo;
	@Autowired
	OrderDetailRepo orderDetailRepo;
	@Autowired
	ProductOrderRepo productOrderRepo;
	
	@Override
	public int getStock(long productId) {
		return productRepo.getStock(productId);
	}

	@Override
	public int getlimit(long productId) {
		return productRepo.getLimit(productId);
	}

	@Override
	public int getExistOrder(long userId) {
		// order status as zero.
		return orderDetailRepo.getExistOrder(userId);
	}

	@Override
	public int createOrder(long userId) {
		OrderDetailEntity orderDetailEntity = OrderDetailEntity.formEntity(userId);
		try{
			orderDetailRepo.save(orderDetailEntity);
			// better to make it as common enum or boolean;
			return 1;
		}
		catch(IllegalArgumentException e) {
			return 0;
		}
	}

	@Override
	public void delete(ProductOrderRequest productOrderRequest) {
		if(isEmpty(productOrderRequest.getProductId()) && isEmpty(productOrderRequest.getOrderId())) {
			productOrderRepo.deleteByIds(productOrderRequest.getOrderId(),productOrderRequest.getProductId());
		}
	}

	@Override
	public void insert(ProductOrderRequest productOrderRequest) {
		
	}

	@Override
	public int getTotalQuantity(long orderId) {
		return 0;
	}

	@Override
	public int getTotalAmount(long orderId) {
		return 0;
	}

	@Override
	public List<StockViolatedEntity> getStockViolatedDetail(long orderId) {
		return null;
	}

	@Override
	public int getOrderCount(long userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isOrderExist(long orderId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Integer getProductOrderedCount(long orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InvoiceOrderDetail> getOrderResponse(long orderId) {
		// TODO Auto-generated method stub
		return null;
	}

}
