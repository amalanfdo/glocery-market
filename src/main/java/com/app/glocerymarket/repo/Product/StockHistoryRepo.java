package com.app.glocerymarket.repo.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.glocerymarket.entity.product.StockHistoryEntity;

public interface StockHistoryRepo extends JpaRepository<StockHistoryEntity, Long> {

}
