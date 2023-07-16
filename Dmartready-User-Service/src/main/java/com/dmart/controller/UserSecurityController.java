package com.dmart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dmart.entity.User;
import com.dmart.entity.UserRole;
import com.dmart.repository.UserRepositoty;

@RestController
@RequestMapping("/user-service-api")
@CrossOrigin("*")
public class UserSecurityController {

	@Autowired
	private UserRepositoty userRepositoty;
	
	@GetMapping("/signIn")
	public ResponseEntity<User> signIn(Authentication authentication) {
		String username = authentication.getName();
		User user = userRepositoty.findByUsername(username)
				.orElseThrow(()-> new BadCredentialsException("No user found with username: "+username));
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@GetMapping("/token-check")
	public ResponseEntity<Boolean> isTokenValid() {
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	@GetMapping("user/admin-check")
	public ResponseEntity<Boolean> isAdmin() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepositoty.findByUsername(username)
				.orElseThrow(()-> new BadCredentialsException("No user found with username: "+username));
		boolean isAdmin = user.getRole() == UserRole.ROLE_ADMIN;
		return new ResponseEntity<Boolean>(isAdmin, HttpStatus.OK);
	}
	
}
