package com.dmart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dmart.entity.Store;
import com.dmart.entity.User;
import com.dmart.entity.UserRole;
import com.dmart.exception.UserException;
import com.dmart.repository.UserRepositoty;
import com.dmart.restclients.StoreServiceClient;
import com.dmart.service.UserService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepositoty userRepositoty;

	@Autowired
	private StoreServiceClient storeClient;

	/**
	 * This method only should be called by Admin
	 */
	@Override
	public User registerNewUser(User user) {
		return userRepositoty.save(user);
	}
	
	@Override
	public User getUserById(Integer userId) {
		User user = userRepositoty.findById(userId).orElseThrow(() -> new UserException(noUserMsg(userId)));
		return user;
	}

	@Override
	public User getUserByUsername(String username) {
		User user = userRepositoty.findByUsername(username).orElseThrow(() -> new UserException(noUserMsg(username)));
		return user;
	}

	@Override
	public Boolean checkUserExists(Integer userId) {
		boolean ifUserExists = userRepositoty.existsById(userId);
		return ifUserExists;
	}

	@Override
	public Store getStoreOfUser(Integer userId) {
		Store store = storeClient.viewStoreByMangerId(userId).getBody();
		return store;
	}

	private String noUserMsg(Object identifier) {
		return "No user found with Identifier (ID/Username): " + (identifier.toString());
	}
}
