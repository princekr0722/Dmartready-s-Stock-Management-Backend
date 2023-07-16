package com.dmart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.dmart.dto.PageOf;
import com.dmart.dto.ProductCreationForm;
import com.dmart.dto.ProductUpdateForm;
import com.dmart.entity.Product;
import com.dmart.entity.ProductCategory;
import com.dmart.exception.ProductException;
import com.dmart.repository.ProductRepository;
import com.dmart.service.OperationType;
import com.dmart.service.ProductCategoryService;
import com.dmart.service.ProductService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductCategoryService categoryService;

	@Override
	public Product addNewProduct(ProductCreationForm form) {
		String productName = form.getProdutName();
		Integer categoryId = form.getCategoryId();
		Long stocks = form.getTotalStocks();
		
		ProductCategory category = null;
		if(categoryId != null) category = categoryService.getCategoryById(categoryId);
		
		Product unsavedProduct =  new Product(productName, category, stocks);
		Product newProduct = productRepository.save(unsavedProduct);
		return newProduct;
	}
	
	@Override
	public Product getProductById(Integer productId) {
		Product product = productRepository.findById(productId)
				.orElseThrow(()-> new ProductException(getNoProductMsg(productId)));
		return product;
	}

	@Override
	public Product updateProductById(ProductUpdateForm form) {
		Product existingProduct = getProductById(form.getProductId());
		existingProduct.setCategory(
				categoryService.getCategoryById(form.getCategoryId())
		);
		existingProduct.setName(form.getNewName());
		existingProduct.setTotalStocks(form.getTotalStocks());
		
		Product updatedProduct = productRepository.save(existingProduct);
		return updatedProduct;
	}

	@Override
	public Product deleteProductById(Integer productId) {
		Product product = getProductById(productId);
		
		productRepository.deleteById(productId);
		return product;
	}

	@Override
	public Long editTotalStock(Integer productId, OperationType operationType, Integer noOfStocks) {
		if(noOfStocks <= 0) {
			throw new ProductException("No of stocks is less than of equals to 0");
		}
		Long currentStock = productRepository.getTotalStockOf(productId);
		if(currentStock == null) {
			throw new ProductException(getNoProductMsg(productId));
		}
		
		Long newStock;
		if(operationType == OperationType.INCREMENT) {
			productRepository.editProductStockBy(productId, noOfStocks);
			
			newStock = currentStock + noOfStocks;
		} else {
			if(currentStock < noOfStocks) {
				throw new ProductException("Product's stock is lesser than the number of stocks to decrement");
			} 
			productRepository.editProductStockBy(productId, -1*noOfStocks);
			
			newStock = currentStock - noOfStocks;
		}
		
//		TODO Show real time update of Product's stock in Head side;
		
		return newStock;
	}

	@Override
	public PageOf<Product> getProductsPage(Integer pageSize, Integer pageNumber, Direction orderBy) {
		if(pageSize<=0) {
			throw new ProductException("Page size must not be less than or equals to 0");
		}
		if(pageNumber<=0) {
			throw new ProductException("Page number must not be less than or equals to 0");
		}
		pageNumber--;
		Sort sort = Sort.by(orderBy, "id");
		Page<Product> productPage = productRepository.findAll(
			PageRequest.of(pageNumber, pageSize, sort)
		);
		return new PageOf<>(productPage);
	}

	@Override
	public Long getTotalNoOfStocks(Integer productId) {
		Long currentStock = productRepository.getTotalStockOf(productId);
		if(currentStock == null) {
			throw new ProductException(getNoProductMsg(productId));
		}
			
		return currentStock;
	}
	
	private String getNoProductMsg(Integer productId) {
		return "No product available with Id: "+productId;
	}
}
