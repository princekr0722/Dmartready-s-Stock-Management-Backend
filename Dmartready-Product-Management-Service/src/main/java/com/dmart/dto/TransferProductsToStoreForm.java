package com.dmart.dto;

import java.util.ArrayList;
import java.util.List;

import com.dmart.entity.ProductInStore;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 This class is a transactional form for Product's relocation.
 */

@Getter
@Setter
@NoArgsConstructor
public class TransferProductsToStoreForm {
	
	private List<ProductStoreQuantityDto> itemsDetails = new ArrayList<>();
	
	@Column(nullable = false)
	private Integer storeId;

	public TransferProductsToStoreForm(List<ProductStoreQuantityDto> productStoreQuantitys, Integer storeId) {
		this.itemsDetails  = productStoreQuantitys;
		this.storeId = storeId;
	}

}
