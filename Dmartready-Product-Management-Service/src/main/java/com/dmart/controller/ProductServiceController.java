package com.dmart.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dmart.dto.PageOf;
import com.dmart.dto.ProductCreationForm;
import com.dmart.dto.ProductUpdateForm;
import com.dmart.dto.StoreProductDetails;
import com.dmart.dto.TransferProductsToStoreForm;
import com.dmart.entity.Product;
import com.dmart.entity.ProductInStore;
import com.dmart.service.OperationType;
import com.dmart.service.ProductService;
import com.dmart.service.ProductsAndStoreService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/product-service-api")
@CrossOrigin("*")
public class ProductServiceController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductsAndStoreService productsInStoreService;

	@PostMapping("/product")
	public ResponseEntity<Product> addNewProduct(@Valid @RequestBody ProductCreationForm form) {
		Product newProduct = productService.addNewProduct(form);
		return new ResponseEntity<Product>(newProduct, HttpStatus.CREATED);
	}

	@GetMapping("/product/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable Integer productId) {
		Product product = productService.getProductById(productId);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@PutMapping("/product")
	public ResponseEntity<Product> updateProductById(@Valid @RequestBody ProductUpdateForm productUpdateForm) {
		Product updatedProduct = productService.updateProductById(productUpdateForm);
		return new ResponseEntity<Product>(updatedProduct, HttpStatus.OK);
	}

	@DeleteMapping("/product/{productId}")
	public ResponseEntity<Product> deleteProductById(@PathVariable Integer productId) {
		Product deletedProduct = productService.deleteProductById(productId);
		return new ResponseEntity<>(deletedProduct, HttpStatus.OK);
	}

	@PatchMapping("/product/{productId}/edit/stocks")
	public ResponseEntity<Map<String, Long>> editTotalStock(@PathVariable Integer productId, @RequestParam OperationType operationType,@RequestParam Integer noOfStocks) {
		Long updatedStock = productService.editTotalStock(productId, operationType, noOfStocks);
		Map<String, Long> map = new HashMap<>();
		map.put("Updated stock", updatedStock);
		return new ResponseEntity<Map<String,Long>>(map, HttpStatus.OK);
	}

	@GetMapping("/products")
	public ResponseEntity<PageOf<Product>> getProductsPage(@RequestParam Integer pageSize, @RequestParam Integer pageNumber, @RequestParam(required = false) Direction orderBy) {
		if(orderBy==null) orderBy = Direction.ASC;
		PageOf<Product> page = productService.getProductsPage(pageSize, pageNumber, orderBy);
		return new ResponseEntity<PageOf<Product>>(page, HttpStatus.OK);
	}

	@GetMapping("/product/{productId}/stocks")
	public ResponseEntity<Map<String,Long>> getTotalNoOfStocks(Integer productId) {
		Long currentNoOfStock = productService.getTotalNoOfStocks(productId);
		Map<String, Long> map = new HashMap<>();
		map.put("Current stock", currentNoOfStock);
		return new ResponseEntity<Map<String,Long>>(map, HttpStatus.OK);
	}

	@PostMapping("/products/shift/store")
	public ResponseEntity<List<ProductInStore>> transferProductsToStore(@Valid @RequestBody TransferProductsToStoreForm form) {
		List<ProductInStore> productsInStore = productsInStoreService.transferProductsToStore(form);
		return new ResponseEntity<List<ProductInStore>>(productsInStore, HttpStatus.OK);
	}
	
	@GetMapping("/store/{storeId}/products")
	ResponseEntity<PageOf<StoreProductDetails>> getProductsInStore(
			@PathVariable Integer storeId, 
			@RequestParam Integer pageSize, 
			@RequestParam Integer pageNumber, 
			@RequestParam(required = false) Direction orderBy
	) {
		PageOf<StoreProductDetails> pageOfProducts = productsInStoreService.getProductsInStore(storeId, pageSize, pageNumber, orderBy);
		return new ResponseEntity<PageOf<StoreProductDetails>>(pageOfProducts, HttpStatus.OK);
	}
	
	@GetMapping("/store/{storeId}/product/{productId}")
	ResponseEntity<StoreProductDetails> getProductOfStoreBy(@PathVariable Integer storeId, @PathVariable Integer productId) {
		StoreProductDetails storeProductDetails = productsInStoreService.getProductOfStoreBy(storeId, productId);
		return new ResponseEntity<StoreProductDetails>(storeProductDetails, HttpStatus.OK);
	}
}
