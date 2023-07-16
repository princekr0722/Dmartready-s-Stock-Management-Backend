package com.dmart.restclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("DMART-USER-SERVICE")
public interface UserServiceClient {

	@GetMapping("/user-service-api/user/{userId}/store/{storeId}/check-exists")
	ResponseEntity<Boolean> checkUserHasStore(@PathVariable Integer userId, @PathVariable Integer storeId);
	
	@GetMapping("/user-service-api/user/{userId}/check-exists")
	public ResponseEntity<Boolean> checkUserExists(@PathVariable Integer userId);
	
	@GetMapping("/user-service-api/token-check")
	public ResponseEntity<Boolean> isTokenValid(@RequestHeader("Authorization") String jwt);
	
	@GetMapping("/user-service-api/user/admin-check")
	public ResponseEntity<Boolean> isAdmin(@RequestHeader("Authorization") String jwt);
}
