package com.dmart.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.dmart.dto.PageOf;
import com.dmart.entity.Address;
import com.dmart.entity.Product;
import com.dmart.entity.Store;
import com.dmart.exception.StoreException;
import com.dmart.repositoy.AddressRepositoy;
import com.dmart.repositoy.StoreRepository;
import com.dmart.restclients.UserServiceClient;
import com.dmart.service.StoreService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class StoreServiceImpl implements StoreService{

	@Autowired
	private StoreRepository storeRepository;
	
	@Autowired
	private UserServiceClient userClient;
	
	@Override
	public Store addNewStore(Store store) {
		store.setStoreManagerId(null);
		store.getAddress().setStore(store);
		Store newStore = storeRepository.save(store);
		return newStore;
	}

	@Override
	public Store viewStoreById(Integer storeId) {
		Optional<Store> opt = storeRepository.findById(storeId);
		if(opt.isPresent()) {
			
			return opt.get();
		} else {
			throw new StoreException(NoStoreAvailableMsg(storeId));
		}
	}
	
	public Store viewStoreByMangerId(Integer managerId) {
		Store store = storeRepository.findByStoreManagerId(managerId)
				.orElseThrow(()-> new StoreException("No store exists who's manager has Id: "+managerId));
		
		return store;
	}
	
	public boolean storeExistsById(Integer storeId) {
		return storeRepository.existsById(storeId);
	}
	
	@Override
	public String updateStore(Integer storeId, Store updatedStore) {
		Store existingStore = storeRepository.findById(storeId)
	            .orElseThrow(() -> new StoreException(NoStoreAvailableMsg(storeId)));
		
		Address existingAddress = existingStore.getAddress();
	    Address updatedAddress = updatedStore.getAddress();
	    
	    existingAddress.setAddressLine1(updatedAddress.getAddressLine1());
	    existingAddress.setCity(updatedAddress.getCity());
	    existingAddress.setCountry(updatedAddress.getCountry());
	    existingAddress.setPinCode(updatedAddress.getPinCode());
	    existingAddress.setState(updatedAddress.getState());
	    
		storeRepository.save(existingStore);
		
		return "Store is updated with Id: "+storeId;
			
	}

	/*
	@Override
	public String updateStore(Integer storeId, Store store) {
		store.setId(storeId);
		boolean storeExists = storeRepository.existsById(storeId);
		if(storeExists) {
			Address address = store.getAddress();
			
			address.setStore(store);
			Store updatedStore = storeRepository.save(store);
			
			addressRepositoy.save(address);
			return "Store is updated with Id: "+storeId;
			
		} else {
			throw new StoreException(NoStoreAvailableMsg(storeId));
		}
	}
	*/

	@Override
	public Store deleteStoreById(Integer storeId) {
		Optional<Store> opt = storeRepository.findById(storeId);
		if(opt.isPresent()) {
			
			storeRepository.deleteById(storeId);
			return opt.get();
			
		} else {
			throw new StoreException(NoStoreAvailableMsg(storeId));
		}
	}

	@Override
	public PageOf<Store> getStoresInPages(Integer pageSize, Integer pageNumber, Direction orderBy) {
		if(pageSize<=0) {
			throw new StoreException("Page size must not be less than or equals to 0");
		}
		if(pageNumber<=0) {
			throw new StoreException("Page number must not be less than or equals to 0");
		}
		
		pageNumber--;
		if(orderBy == null) orderBy = Direction.ASC;
		Sort sort = Sort.by(orderBy, "id");
		Page<Store> page = storeRepository.findAll(PageRequest.of(pageNumber, pageSize, sort));
		
		return new PageOf<>(page);
	}
	
	
	private String NoStoreAvailableMsg(Integer storeId) {		
		return "No store available with Id: "+storeId;
	}

	@Override
	public PageOf<Product> getProductsInStore(Integer storeId, Integer pageSize, Integer pageNuInteger,
			Direction orderBy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product getProductById(Integer storeId, Integer productId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Store assignManagerToStore(Integer storeId, Integer managerId) {
		
		Store store = viewStoreById(storeId);
		if(store.getStoreManagerId() != null) {
			throw new StoreException("Store with Id "+storeId+" already has a manager");
		}
		
		boolean userExists = userClient.checkUserExists(managerId).getBody();
		if(!userExists) throw new StoreException("No manager exist with Id: "+managerId); 
		
		boolean managerHasStore = storeRepository.checkManagerHasStore(managerId);
		if(managerHasStore) throw new StoreException("Manager already has a store.");
		
		store.setStoreManagerId(managerId);
		
		
		return storeRepository.save(store);
	}
}
