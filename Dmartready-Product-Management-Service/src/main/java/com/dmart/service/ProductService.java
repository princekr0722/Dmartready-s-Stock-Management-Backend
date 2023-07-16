package com.dmart.service;

import org.springframework.data.domain.Sort.Direction;

import com.dmart.dto.PageOf;
import com.dmart.dto.ProductCreationForm;
import com.dmart.dto.ProductUpdateForm;
import com.dmart.entity.Product;

public interface ProductService {
	
	Product addNewProduct(ProductCreationForm form);
	Product getProductById(Integer productId);
	Product updateProductById(ProductUpdateForm productUpdateForm);
	Product deleteProductById(Integer productId);
	Long editTotalStock(Integer productId, OperationType operationType , Integer noOfStocks);
	PageOf<Product> getProductsPage(Integer pageSize, Integer pageNumber, Direction orderBy);
	
	Long getTotalNoOfStocks(Integer productId);
}
