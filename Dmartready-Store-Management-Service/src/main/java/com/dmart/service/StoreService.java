package com.dmart.service;

import org.springframework.data.domain.Sort.Direction;

import com.dmart.dto.PageOf;
import com.dmart.entity.Product;
import com.dmart.entity.Store;

public interface StoreService {
	
	Store addNewStore(Store store);
	Store viewStoreById(Integer storeId);
	Store viewStoreByMangerId(Integer managerId);
	boolean storeExistsById(Integer storeId);
	String updateStore(Integer storeId, Store store);
	Store deleteStoreById(Integer storeId);
	PageOf<Store> getStoresInPages(Integer pageSize, Integer pageNumber, Direction orderBy);
	
	PageOf<Product> getProductsInStore(Integer storeId, Integer pageSize, Integer pageNuInteger, Direction orderBy);
	Product getProductById(Integer storeId, Integer productId);
	Store assignManagerToStore(Integer storeId, Integer managerId);
	
}
