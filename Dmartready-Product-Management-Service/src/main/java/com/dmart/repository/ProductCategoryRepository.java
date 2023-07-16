package com.dmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmart.entity.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer>{
	
}
