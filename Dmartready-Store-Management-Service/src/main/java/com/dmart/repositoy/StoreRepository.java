package com.dmart.repositoy;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.dmart.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Integer>, PagingAndSortingRepository<Store, Integer>{

	Optional<Store> findByStoreManagerId(Integer storeManagerId);
	
	@Query("SELECT CASE WHEN COUNT(s) = 0 THEN false ELSE true END FROM Store s WHERE s.id = :storeId AND s.storeManagerId IS NULL")
	Boolean ifStoreHaManager(Integer storeId);
	
	@Modifying
	@Query("UPDATE Store SET storeManagerId = :managerId WHERE id = :storeId")
	int assignManagerToStore(Integer storeId, Integer managerId);
	
	@Query("SELECT CASE WHEN COUNT(s) = 0 THEN false ELSE true END FROM Store s WHERE s.storeManagerId = :managerId")
	Boolean checkManagerHasStore(Integer managerId);
	
}
