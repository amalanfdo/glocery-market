package com.app.glocerymarket.repo.Product;

import org.springframework.data.repository.CrudRepository;

import com.app.glocerymarket.entity.product.CategoryEntity;

public interface CategoryRepo extends CrudRepository<CategoryEntity, Long> {

}
