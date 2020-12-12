package com.app.glocerymarket.api.product;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.glocerymarket.entity.product.CategoryEntity;
import com.app.glocerymarket.entity.product.ProductEntity;
import com.app.glocerymarket.entity.requestentity.ProductRequestFilter;
import com.app.glocerymarket.exception.product.AgeConstraintError;
import com.app.glocerymarket.exception.product.ProductNotFoundException;
import com.app.glocerymarket.service.product.ProductService;
import com.app.glocerymarket.exception.product.ProductNotFoundException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponses;
// mainly for portal page development.
@RestController(value = "store/product")
@Api( tags ={ "api contains opration of product detail"})
public class ProductController {

	@Autowired
	ProductService productService;
	
	@RequestMapping(value = "/get-product-details" ,method=RequestMethod.POST)
	@ResponseBody
	public List<ProductEntity> getProductDetails(ProductRequestFilter productRequestFilter) {
		if(Objects.isNull(productRequestFilter)) {
			productRequestFilter = ProductRequestFilter.init();
		}else if(!ProductRequestFilter.isValidParam(productRequestFilter)) {
			throw new ProductNotFoundException();
		}
		return productService.getProductDetails(productRequestFilter);
	}
	
	@RequestMapping(value = "/get-categories" ,method=RequestMethod.POST)
	public List<CategoryEntity> getCategories(@RequestParam(value="age") int age) {
		if(age<=8) {
			throw new AgeConstraintError();
		}
		return productService.getCategories(age);
	}
	
	
	
	
	
}
