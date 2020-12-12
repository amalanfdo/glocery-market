package com.app.glocerymarket.repo.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.glocerymarket.entity.product.ProductEntity;

public interface ProductRepo extends JpaRepository<ProductEntity,Long> {

	int getStock(long productId);

	int getLimit(long productId);

}
