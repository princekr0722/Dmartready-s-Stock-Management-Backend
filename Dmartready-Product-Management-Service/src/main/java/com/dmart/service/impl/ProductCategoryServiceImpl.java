package com.dmart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmart.entity.ProductCategory;
import com.dmart.exception.CategoryException;
import com.dmart.repository.ProductCategoryRepository;
import com.dmart.service.ProductCategoryService;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService{
	
	@Autowired
	private ProductCategoryRepository categoryRepository;

	@Override
	public ProductCategory addNewCategory(ProductCategory category) {
		ProductCategory newCategory = categoryRepository.save(category);
		return newCategory;
	}

	@Override
	public ProductCategory getCategoryById(Integer categoryId) {
		ProductCategory category = categoryRepository.findById(categoryId)
				.orElseThrow(()-> new CategoryException(noCategoryMsg(categoryId)));
		return category;
	}

	@Override
	public ProductCategory deleteCategoryById(Integer categoryId) {
		ProductCategory category = categoryRepository.findById(categoryId)
				.orElseThrow(()-> new CategoryException(noCategoryMsg(categoryId)));
		categoryRepository.deleteById(categoryId);
		return category;
	}
	
	private String noCategoryMsg(Integer categoryId) {
		String noCategoryMsg = "No category is available with Id: "+categoryId;
		return noCategoryMsg;
	}

	@Override
	public List<ProductCategory> getAllCetegories() {
		return categoryRepository.findAll();
	}
}
