package com.dmart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dmart.entity.ProductCategory;
import com.dmart.service.ProductCategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/product-service-api")
@CrossOrigin("*")
public class ProductCategoryController {

	@Autowired
	private ProductCategoryService categoryService;
	
	@PostMapping("/product/category")
	public ResponseEntity<ProductCategory> addNewCategory(@Valid @RequestBody ProductCategory category) {
		ProductCategory newCategory = categoryService.addNewCategory(category);
		return new ResponseEntity<ProductCategory>(newCategory, HttpStatus.CREATED);
	}

	@GetMapping("/product/category/{categoryId}")
	public ResponseEntity<ProductCategory> getCategoryById(@PathVariable Integer categoryId) {
		ProductCategory category = categoryService.getCategoryById(categoryId);
		return new ResponseEntity<ProductCategory>(category, HttpStatus.OK);
	}

	@DeleteMapping("/product/category/{categoryId}")
	public ResponseEntity<ProductCategory> deleteCategoryById(@PathVariable Integer categoryId) {
		ProductCategory deletedCategory = categoryService.deleteCategoryById(categoryId);
		return new ResponseEntity<ProductCategory>(deletedCategory, HttpStatus.OK);
	}
	
	@GetMapping("/product/categories")
	public ResponseEntity<List<ProductCategory>> getAllCetegories() {
		List<ProductCategory> allCategory = categoryService.getAllCetegories();
		return new ResponseEntity<List<ProductCategory>>(allCategory, HttpStatus.OK);
	}
}
