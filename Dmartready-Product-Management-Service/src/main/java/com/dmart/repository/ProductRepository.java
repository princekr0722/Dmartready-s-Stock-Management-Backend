
package com.dmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.dmart.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>, PagingAndSortingRepository<Product, Integer>{
	
	@Modifying
	@Query("UPDATE Product p SET p.totalStocks = p.totalStocks + :noOfStocks WHERE p.id = :productId")
	Integer editProductStockBy(Integer productId, Integer noOfStocks);
	
	@Query("SELECT p.totalStocks FROM Product p WHERE p.id = :productId")
	Long getTotalStockOf(Integer productId);
}
