package com.app.glocerymarket.api.customer;
import static com.app.glocerymarket.utils.EmptyValidationUtil.isEmpty;
import static com.app.glocerymarket.utils.EmptyValidationUtil.isNotEmpty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.glocerymarket.entity.common.InvoiceOrderDetail;
import com.app.glocerymarket.entity.common.StockViolatedEntity;
import com.app.glocerymarket.entity.requestentity.ProductOrderRequest;
import com.app.glocerymarket.enums.product.OrderAmountConstraintEnum;
import com.app.glocerymarket.enums.product.OrderQuantityContraintEnum;
import com.app.glocerymarket.enums.product.PlaceOrderConstraintEnum;
import com.app.glocerymarket.enums.product.ProductStockConstraintEnum;
import com.app.glocerymarket.exception.product.UserNotFoundException;
import com.app.glocerymarket.service.customer.OrderDetailService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RequestMapping("store/order")
@Api(tags= {"get order detail"})
public class OrderDetailController {

	@Autowired
	OrderDetailService orderDetailService;
	
	
	@RequestMapping(value="/get-stock",method=RequestMethod.GET)
	@ApiOperation(value= "get stock details")
	public int getStock(@RequestParam(value="productId") long productId) {
		if(isEmpty(productId))
			return 0;
		// TODO product exist check throw exception if need
		return orderDetailService.getStock(productId);
	}
	
	@RequestMapping(value="/is-available-stock",method=RequestMethod.POST, produces = {"application/json"})
	@ApiOperation(value= "is stock availale?")
	public ResponseEntity<Boolean> isAvailableStock(@RequestBody ProductOrderRequest productOrderRequest) {
		if(!ProductOrderRequest.isValidParam(productOrderRequest))
			return ResponseEntity.ok(false);
		int stock = orderDetailService.getStock(productOrderRequest.getProductId());
		if(stock >= productOrderRequest.getQuantity()) 
			return ResponseEntity.ok(false);
		return ResponseEntity.ok(true);
	}
	
	@RequestMapping(value="/get-limit" , method=RequestMethod.GET)
	@ApiOperation(value= " api to check limit specified by store")
	public int getLimit(@RequestParam(value="productId") long productId) {
		if(isEmpty(productId))
			return 0;
		// TODO  product exist check throw error if need.
		return orderDetailService.getlimit(productId);
	}
	
	
	@RequestMapping(value="/is-available-limit" , method=RequestMethod.GET, produces = {"application/json"})
	@ApiOperation(value= "is quanity with in limit ?")
	public ResponseEntity<Boolean> isAvailableLimit(@RequestBody ProductOrderRequest productOrderRequest) {
		if(!ProductOrderRequest.isValidParam(productOrderRequest))
			return ResponseEntity.ok(false);
		int limit = orderDetailService.getlimit(productOrderRequest.getProductId());
		if(limit >= productOrderRequest.getQuantity()) 
			return ResponseEntity.ok(false);
		return ResponseEntity.ok(true);
	}
	
	
	@RequestMapping(value="/create-order" , method=RequestMethod.GET, produces = {"application/json"})
	@ApiOperation(value= " api to create order")
	public ResponseEntity<Integer> createOrder(@RequestParam(value="userId") long userId) {
		if(isEmpty(userId))
			throw new UserNotFoundException();
		// TODO  if need pls check is order exsist with status 0.
		int existOrder =  orderDetailService.getExistOrder(userId);
		if(isNotEmpty(existOrder)) {
			return ResponseEntity.ok(existOrder);
		}
		return  ResponseEntity.ok(orderDetailService.createOrder(userId));
	}
	
	@RequestMapping(value="/add-product" , method=RequestMethod.POST, produces = {"application/json"})
	@ApiOperation(value= " api to add product details for user")
	public ResponseEntity<ProductStockConstraintEnum> addProduct(@RequestBody ProductOrderRequest  productOrderRequest) {
		if(ProductOrderRequest.isValidParam(productOrderRequest)) {
			return ResponseEntity.ok(ProductStockConstraintEnum.INVALID_PARAM);
		}
		int quantity = productOrderRequest.getQuantity();
		if(!this.isAvailableLimit(productOrderRequest).getBody()) {
			return ResponseEntity.ok(ProductStockConstraintEnum.LIMIT_EXIST);
		} else if(!this.isAvailableStock(productOrderRequest).getBody()) {
			return ResponseEntity.ok(ProductStockConstraintEnum.STOCK_NOT_AVAILABLE);
		}
		else {
			int quanity = productOrderRequest.getQuantity();
			if(isEmpty(quantity)) {
				orderDetailService.delete(productOrderRequest);
			} else {
				// TODO  otherwise we can use update or [insert for with first check]. worst case it takes three operation.
				// TODO  it is easy think worst case it takes 2 operation.
				orderDetailService.delete(productOrderRequest);
				orderDetailService.insert(productOrderRequest);
			}
		}
		return ResponseEntity.ok(ProductStockConstraintEnum.PRODUCT_UPDATED);
	}
	
	@RequestMapping(value="/place-order" , method=RequestMethod.GET, produces = {"application/json"})
	@ApiOperation(value= "method to check order is placeble")
	public ResponseEntity<PlaceOrderConstraintEnum> placeOrder(@RequestParam(value="orderId")long orderId) {
		int totalQuantity = orderDetailService.getTotalQuantity(orderId);
		if(OrderQuantityContraintEnum.QUANTITY.isQuantityZero(totalQuantity)) {
			return ResponseEntity.ok(PlaceOrderConstraintEnum.QUANTITY_ZERO); 
		} else if(!OrderQuantityContraintEnum.QUANTITY.hasLesserQuantity(totalQuantity)) {
			return ResponseEntity.ok(PlaceOrderConstraintEnum.QUANTITY_GEATER);
		}
		int amount = orderDetailService.getTotalAmount(orderId);
		if(OrderAmountConstraintEnum.AMOUNT.hasGeaterAmount(amount)) {
			return ResponseEntity.ok(PlaceOrderConstraintEnum.AMOUNT_GREATER);
		}
		return ResponseEntity.ok(PlaceOrderConstraintEnum.NO_ERROR);
	}
	
	// select productId, stock-quantity as remainingQuantity 
	// from product inner p join product_order po on (p.product_id = po.product_id) 
	// where order_id=:orderId and remainingQuantity<0;
	@RequestMapping(value="/ordered-stock-violation" , method=RequestMethod.GET, produces = {"application/json"})
	@ApiOperation(value= "check whether order quantity is present in stock")
	public List<com.app.glocerymarket.entity.common.StockViolatedEntity> orderedStockViolation(@RequestParam(value="orderId")long orderId) {
		if(orderId == 0) {
			return new ArrayList<>();
		}	
		List<StockViolatedEntity> stockEntity = orderDetailService.getStockViolatedDetail(orderId);
		//TODO  based on that we can show dialog to the user for item which are not avaiable 
		//TODO  we have negative quantity based on that we can calculate max quanity can be ordered.
		return stockEntity;
	}
	
	
	@RequestMapping(value="/get-total-amount" , method=RequestMethod.GET, produces = {"application/json"})
	@ApiOperation(value= "total amount . it will be called after every item add or removed in checkout page")
	public ResponseEntity<Long> getTotalAmount(@RequestParam(value="orderId")long orderId) {
		if(orderId == 0) {
			return ResponseEntity.ok(0L);
		}
		long totalAmount = orderDetailService.getTotalAmount(orderId);
		return ResponseEntity.ok(totalAmount);
	}
	
	// given discount for repeated user.logic may vary for company. based on assumption.
	// based on assumption if total amount > 100 we can give 10% discount.
	@RequestMapping(value="/get-discount" , method=RequestMethod.GET, produces = {"application/json"})
	@ApiOperation(value= "dicpunt apply function")
	public ResponseEntity<Long> getDiscount(@RequestParam(value="userId")int userId,@RequestParam(value="totalAmount") long totalAmount) {
		if(userId == 0) {
			return ResponseEntity.ok(0L);
		}
		int orderCount = orderDetailService.getOrderCount(userId);
		long discountAmount=0;
		if(totalAmount<100) {
			return ResponseEntity.ok(discountAmount);
		}
		if(orderCount>=2 && orderCount<=10) {
			// between 2 to 10
			totalAmount = (totalAmount*orderCount)/100;
		} else if(orderCount>=10 || orderCount<=1) {
			// less than 1 first time user or above 10 orders
			discountAmount = (totalAmount*10)/100;
		}
		return ResponseEntity.ok(discountAmount);
	}
	
	@ApiOperation(value= "method to get the check out details")
	@RequestMapping(value="/get-checkout-details" , method=RequestMethod.GET, produces = {"application/json"})
	public List<InvoiceOrderDetail> getCheckoutDetails(@RequestParam(value="orderId")long orderId) {
		if(orderId == 0) {
			return new ArrayList<>();
		}
		boolean isOrderExist = orderDetailService.isOrderExist(orderId);
		if(isOrderExist) {
			return orderDetailService.getOrderResponse(orderId);
		}
		return new ArrayList<>();
	}
	
	// checkout bdage load
	@ApiOperation(value= "method to get the check out details")
	@RequestMapping(value="/get-product-ordered-count" , method=RequestMethod.GET, produces = {"application/json"})
	public ResponseEntity<Integer> getProductOrderedCount(@RequestParam(value="orderId")long orderId) {
		if(orderId == 0) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(orderDetailService.getProductOrderedCount(orderId));
	}
	
//	@ApiOperation(value= "purchase-stock-updation")
//	@RequestMapping(value="/purchase-stock-updation" , method=RequestMethod.GET)
//	public ResponseEntity<boolean> PruchaseStockUpdation(PurchaseOrderDetail purchaseOrderDetail) {
//		if(Objects.isNull(purchaseOrderDetail)) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
//		// violation check for order
//		// limit check.
//		// total amount check.
//		// quantity check.
//		
//		return ResponseEntity.ok();
//	}
	
	// hole remove
	// shipment status updation
	// cancel stock updation 
	// feedback updation
	// copy order details functionalities
	
	/**
	 * 
	 * based on previous purschase of customer we need to update the stock for month ? 
	 * it's meaching learning alogorithm (linear regression ) -> error value function with hypothesis h(x)= 0x+02x1
	 * because purchase is a continous one .so we apply linear regression to predict the next month stock.
	 * 
	 * In glocery stores ,we can apply lot of mechaine learning algorithms
	 */
	
}
	

