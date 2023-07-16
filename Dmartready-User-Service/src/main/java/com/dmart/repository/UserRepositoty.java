package com.dmart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.dmart.entity.User;

public interface UserRepositoty extends JpaRepository<User, Integer>{

	Optional<User> findByUsername(String username);
//	Optional<User> findByStoreId(Integer storeId);
	
//	@Query("SELECT CASE WHEN COUNT(u) = 0 THEN false ELSE true END FROM User u WHERE u.id = :userId AND u.storeId = :storeId")
//	Boolean checkUserHasStore(Integer userId, Integer storeId);
	
}
