package com.app.glocerymarket.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.app.glocerymarket.service.customer.CustomerService;

@Configuration
public class ProductConfig {
	
	@Bean
	@Autowired
	CustomerService customerService() {
		
	}
}
