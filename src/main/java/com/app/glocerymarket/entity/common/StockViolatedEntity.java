package com.app.glocerymarket.entity.common;

import javax.persistence.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class StockViolatedEntity {
	private long product_id;
	private int quantity;
}
