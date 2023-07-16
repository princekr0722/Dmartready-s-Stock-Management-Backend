package com.dmart.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductReference {
	private Integer id;
	private String productId;
	private String storeId;
	private Integer noOfStocks;
	private ProductStatus productStatus;
}
