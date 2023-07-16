package com.dmart.restclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dmart.dto.PageOf;
import com.dmart.entity.Product;

@FeignClient("DMART-PRODUCT-SERVICE")
public interface ProductServiceClient {

	@GetMapping("/product-service-api/store/{storeId}/products")
	ResponseEntity<PageOf<Product>> getProductsInStore(
			@PathVariable Integer storeId, 
			@RequestParam Integer pageSize,
			@RequestParam Integer pageNumber,
			@RequestParam(required = false) Direction orderBy		
	);
	
	@GetMapping("/product-service-api/store/{storeId}/product/{productId}")
	ResponseEntity<Product> getProductOfStoreBy(@PathVariable Integer storeId, @PathVariable Integer productId);
	
}
