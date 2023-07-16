package com.dmart.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.dmart.dto.StoreProductDetails;
import com.dmart.entity.ProductInStore;

public interface ProductInStoreRepositoty extends JpaRepository<ProductInStore, Integer>, PagingAndSortingRepository<ProductInStore, Integer>{
	
//	@Modifying
//	@Query("UPDATE ProductReference pr SET pr.noOfStocks = pr.noOfStocks + :noOfStocks WHERE pr.id = :productId RETURNING p.noOfStocks")
//	Long editProductStockBy(Integer productId, Long noOfStocks);
//	
//	@Modifying
//	@Query("UPDATE ProductReference pr SET pr.productReferenceStatus = :status WHERE pr.")
	
	@Query("SELECT ps FROM ProductInStore ps WHERE ps.product.id = :productId AND ps.storeId = :storeId")
	ProductInStore getProductInStoreRecordBy(Integer productId, Integer storeId);
	
	@Query("SELECT new com.dmart.dto.StoreProductDetails(ps.product, ps.quantity) FROM ProductInStore ps WHERE ps.storeId = :storeId")
	Page<StoreProductDetails> getProductsOfStorePageWise(Integer storeId, Pageable pageable);
	
	@Query("SELECT new com.dmart.dto.StoreProductDetails(ps.product, ps.quantity) FROM ProductInStore ps WHERE ps.product.id = :productId AND ps.storeId = :storeId")
	StoreProductDetails getProductInStoreBy(Integer productId, Integer storeId);
	
}
