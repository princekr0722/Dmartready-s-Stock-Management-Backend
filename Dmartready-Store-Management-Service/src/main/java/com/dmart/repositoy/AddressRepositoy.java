package com.dmart.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.dmart.entity.Address;

public interface AddressRepositoy extends JpaRepository<Address, Integer>, PagingAndSortingRepository<Address, Integer>{

	
	
}
