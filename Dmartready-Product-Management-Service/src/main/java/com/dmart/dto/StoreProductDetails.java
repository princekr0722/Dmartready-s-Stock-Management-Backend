package com.dmart.dto;

import com.dmart.entity.Product;
import com.dmart.entity.ProductCategory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreProductDetails {

	private Integer id;
	private String name;
	private ProductCategory category;
	private Integer totalStocks;
	
	public StoreProductDetails(Product product, Integer totalStockInStore) {
		this.id = product.getId();
		this.name = product.getName();
		this.category = product.getCategory();
		this.totalStocks = totalStockInStore;
	}
}
