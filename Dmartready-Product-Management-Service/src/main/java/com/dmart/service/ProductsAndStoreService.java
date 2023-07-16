package com.dmart.service;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;

import com.dmart.dto.PageOf;
import com.dmart.dto.StoreProductDetails;
import com.dmart.dto.TransferProductsToStoreForm;
import com.dmart.entity.Product;
import com.dmart.entity.ProductInStore;

public interface ProductsAndStoreService {
	
	List<ProductInStore> transferProductsToStore(TransferProductsToStoreForm form);
	
	PageOf<StoreProductDetails> getProductsInStore(Integer storeId, Integer pageSize, Integer pageNumber, Direction orderBy);
	StoreProductDetails getProductOfStoreBy(Integer storeId, Integer productId);
}
