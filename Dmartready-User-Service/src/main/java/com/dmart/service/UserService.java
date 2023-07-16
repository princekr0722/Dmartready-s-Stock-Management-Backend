package com.dmart.service;

import com.dmart.entity.Store;
import com.dmart.entity.User;

public interface UserService {

	User registerNewUser(User user);
	User getUserById(Integer userId);
	User getUserByUsername(String username);
	
	Boolean checkUserExists(Integer userId);
	Store getStoreOfUser(Integer userId);
//	Boolean checkUserHasStore(Integer userId, Integer storeId);
}
