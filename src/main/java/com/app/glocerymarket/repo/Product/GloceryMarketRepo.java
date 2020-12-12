package com.app.glocerymarket.repo.Product;

import org.springframework.data.repository.CrudRepository;

import com.app.glocerymarket.entity.product.GroceryStoreEntity;

public interface GloceryMarketRepo extends CrudRepository<GroceryStoreEntity, Long> {

}
