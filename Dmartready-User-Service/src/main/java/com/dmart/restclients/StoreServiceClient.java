package com.dmart.restclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.dmart.entity.Store;

@FeignClient("DMART-STORE-SERVICE")
public interface StoreServiceClient {
	
	@GetMapping("/store-service-api/store/{storeId}/check-exists")
	ResponseEntity<Boolean> storeExistsById(@PathVariable Integer storeId);
	
	@GetMapping("/store-service-api/manager/{managerId}/store")
	public ResponseEntity<Store> viewStoreByMangerId(@PathVariable Integer managerId);
	
	@PostMapping("/store-service-api/store")
	public ResponseEntity<Store> addNewStore(@RequestBody Store store);
	
	@PutMapping("/store-service-api/store/{storeId}/assign/manager/{managerId}")
	public ResponseEntity<Store> assignManagerToStore(@PathVariable("storeId") Integer storeId, @PathVariable("managerId") Integer managerId);
	
	@DeleteMapping("/store-service-api/store/{storeId}")
	public ResponseEntity<Store> deleteStoreById(@PathVariable("storeId") Integer storeId);
}
