package com.app.glocerymarket.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.app.glocerymarket.exception.customer.CustomerNotFoundException;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
	@ExceptionHandler(value = CustomerNotFoundException.class)
	public ResponseEntity<Object> handleCustomerException(CustomerNotFoundException e) {
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
}
