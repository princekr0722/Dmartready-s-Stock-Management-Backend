package com.dmart.service;

import java.util.List;

import com.dmart.entity.ProductCategory;

public interface ProductCategoryService {
	
	ProductCategory addNewCategory(ProductCategory category);
	ProductCategory getCategoryById(Integer categoryId);
	ProductCategory deleteCategoryById(Integer categoryId);
	List<ProductCategory> getAllCetegories();
	
}
