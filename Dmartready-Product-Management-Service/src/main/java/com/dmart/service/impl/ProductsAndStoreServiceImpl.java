package com.dmart.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.dmart.dto.PageOf;
import com.dmart.dto.ProductStoreQuantityDto;
import com.dmart.dto.StoreProductDetails;
import com.dmart.dto.TransferProductsToStoreForm;
import com.dmart.entity.Product;
import com.dmart.entity.ProductInStore;
import com.dmart.exception.ProductException;
import com.dmart.repository.ProductInStoreRepositoty;
import com.dmart.service.OperationType;
import com.dmart.service.ProductService;
import com.dmart.service.ProductsAndStoreService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductsAndStoreServiceImpl implements ProductsAndStoreService {

	@Autowired
	private ProductInStoreRepositoty productInStoreRepositoty;
	
	@Autowired
	private ProductService productService;
	
	@Override
	public List<ProductInStore> transferProductsToStore(TransferProductsToStoreForm form) {
		List<ProductStoreQuantityDto> itemsDetails = form.getItemsDetails();
		Integer storeId = form.getStoreId();
		
		List<ProductInStore> productsInStore = itemsDetails.stream().map((i)->{
			
			Integer productId = i.getProductId();
			Product product = productService.getProductById(productId);
			Integer quantity = i.getQuantity();
			
			if(quantity <= product.getTotalStocks()) productService.editTotalStock(productId, OperationType.DECREMENT, quantity);
			else throw new ProductException("Total Stock of product with Id "+productId+" is "+product.getTotalStocks()+" which is lesser than "+quantity+" which is the requested quantity to ship");
			
			ProductInStore existingProductInStore = productInStoreRepositoty.getProductInStoreRecordBy(productId, storeId);
			if(existingProductInStore == null) {
				ProductInStore newProductsAndStore = new ProductInStore(product, storeId, quantity);
				return productInStoreRepositoty.save(newProductsAndStore);				
			} else {
				existingProductInStore.setQuantity(existingProductInStore.getQuantity()+quantity);
				return productInStoreRepositoty.save(existingProductInStore);
			}
			
		}).collect(Collectors.toList());
		
//		TODO real-time update in store and warehouse
 
		return productsInStore;
	}

	@Override
	public PageOf<StoreProductDetails> getProductsInStore(Integer storeId, Integer pageSize, Integer pageNumber,
			Direction orderBy) {
		if(pageSize<=0) {
			throw new ProductException("Page size must not be less than or equals to 0");
		}
		if(pageNumber<=0) {
			throw new ProductException("Page number must not be less than or equals to 0");
		}
		pageNumber--;
		
		if(orderBy == null) orderBy = Direction.ASC;
		Sort sort = Sort.by(orderBy, "id");

		Page<StoreProductDetails> productPage = productInStoreRepositoty.getProductsOfStorePageWise(storeId, PageRequest.of(pageNumber, pageSize, sort));
		return new PageOf<>(productPage);
	}

	@Override
	public StoreProductDetails getProductOfStoreBy(Integer storeId, Integer productId) {
		StoreProductDetails storeProductDetails = productInStoreRepositoty.getProductInStoreBy(productId, storeId);
		if(storeProductDetails == null) throw new ProductException("Store with Id "+storeId+" has no product with Id: "+productId);
		return storeProductDetails;
	}

}
