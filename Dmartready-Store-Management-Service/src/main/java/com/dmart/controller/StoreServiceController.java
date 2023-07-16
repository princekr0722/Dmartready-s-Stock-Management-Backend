package com.dmart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dmart.dto.PageOf;
import com.dmart.entity.Product;
import com.dmart.entity.Store;
import com.dmart.restclients.ProductServiceClient;
import com.dmart.service.StoreService;

@RestController
@RequestMapping("/store-service-api")
@CrossOrigin("*")
public class StoreServiceController {

	@Autowired
	private StoreService storeService;
	
	@Autowired
	private ProductServiceClient productClient;

	@PostMapping("/store")
	public ResponseEntity<Store> addNewStore(@RequestBody Store store) {
		return new ResponseEntity<Store>(storeService.addNewStore(store), HttpStatus.CREATED);
	}

	@GetMapping("/store/{storeId}")
	public ResponseEntity<Store> viewStoreById(@PathVariable Integer storeId) {
		return new ResponseEntity<Store>(storeService.viewStoreById(storeId), HttpStatus.OK);
	}
	
	@GetMapping("/manager/{managerId}/store")
	public ResponseEntity<Store> viewStoreByMangerId(@PathVariable Integer managerId) {
		Store store = storeService.viewStoreByMangerId(managerId);
		return new ResponseEntity<Store>(store, HttpStatus.OK);
	}

	@GetMapping("/store/{storeId}/check-exists")
	public ResponseEntity<Boolean> storeExistsById(@PathVariable Integer storeId) {
		Boolean ifExists = storeService.storeExistsById(storeId);
		return new ResponseEntity<Boolean>(ifExists, HttpStatus.OK);
	}
	
	@PutMapping("/store/{storeId}")
	public ResponseEntity<String> updateStore(
			@PathVariable Integer storeId, 
			@RequestBody Store store
	) {
		return new ResponseEntity<String>(storeService.updateStore(storeId, store), HttpStatus.OK);
	}

	@DeleteMapping("/store/{storeId}")
	public ResponseEntity<Store> deleteStoreById(@PathVariable Integer storeId) {
		return new ResponseEntity<Store>(storeService.deleteStoreById(storeId), HttpStatus.OK);
	}

	@GetMapping("/stores")
	public ResponseEntity<PageOf<Store>> getStoresInPages(
			@RequestParam(required = true) Integer pageSize,
			@RequestParam(required = true) Integer pageNumber,
			@RequestParam(required = false) Direction orderBy
	) {
		return new ResponseEntity<>(storeService.getStoresInPages(pageSize, pageNumber, orderBy), HttpStatus.OK);
	}
	
	@GetMapping("/store/{storeId}/products")
	ResponseEntity<PageOf<Product>> getProductsInStore(
			@PathVariable Integer storeId, 
			@RequestParam Integer pageSize,
			@RequestParam Integer pageNumber,
			@RequestParam(required = false) Direction orderBy		
	) {
		PageOf<Product> pageOfProducts = productClient.getProductsInStore(storeId, pageSize, pageNumber, orderBy).getBody();
		return new ResponseEntity<PageOf<Product>>(pageOfProducts, HttpStatus.OK);
	}
	
	@GetMapping("/store/{storeId}/product/{productId}")
	ResponseEntity<Product> getProductById(@PathVariable Integer storeId, @PathVariable Integer productId) {
		Product product = productClient.getProductOfStoreBy(storeId, productId).getBody();
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@PutMapping("/store/{storeId}/assign/manager/{managerId}")
	public ResponseEntity<Store> assignManagerToStore(@PathVariable Integer storeId, @PathVariable Integer managerId) {
		Store store = storeService.assignManagerToStore(storeId, managerId);
		return new ResponseEntity<Store>(store, HttpStatus.OK);
	}
	
//	...other methods could be here such as: Sell product, Delete products, Edit products and etc...
}
