package com.dmart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dmart.entity.Store;
import com.dmart.entity.User;
import com.dmart.securityconfig.SpringSecurityConstants;
import com.dmart.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user-service-api")
@CrossOrigin("*")
public class UserServiceController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/user/register")
	public ResponseEntity<User> registerNewUser(@Valid @RequestBody User user, HttpServletRequest request) {
		String password = request.getHeader(SpringSecurityConstants.USER_CREATION_HEADER);
		if(password == null || !password.equals(SpringSecurityConstants.USER_CREATION_PASS)) 
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User newUser = userService.registerNewUser(user);
		return new ResponseEntity<User>(newUser, HttpStatus.OK);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable Integer userId) {
		User user = userService.getUserById(userId);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@GetMapping("/user")
	public ResponseEntity<User> getUserByUsername(@RequestParam String username) {
		User user = userService.getUserByUsername(username);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@GetMapping("/user/{userId}/check-exists")
	public ResponseEntity<Boolean> checkUserExists(@PathVariable Integer userId) {
		Boolean ifUserExists = userService.checkUserExists(userId);
		return new ResponseEntity<Boolean>(ifUserExists, HttpStatus.OK);
	}

	@GetMapping("/user/{userId}/store")
	public ResponseEntity<Store> getStoreOfUser(@PathVariable Integer userId) {
		Store store = userService.getStoreOfUser(userId);
		return new ResponseEntity<Store>(store,HttpStatus.OK);
	}

//	@GetMapping("/user/{userId}/store/{storeId}/check-exists")
//	ResponseEntity<Boolean> checkUserHasStore(@PathVariable Integer userId, @PathVariable Integer storeId) {
//		Boolean ifUserHasStore = userService.checkUserHasStore(userId, storeId);
//		return new ResponseEntity<Boolean>(ifUserHasStore, HttpStatus.OK);
//	}
	
}
