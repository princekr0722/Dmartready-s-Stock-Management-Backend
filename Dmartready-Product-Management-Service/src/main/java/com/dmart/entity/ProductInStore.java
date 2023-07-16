package com.dmart.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 This class represent the each products available in each store
 */

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductInStore {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty(access = Access.READ_ONLY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "productId")
	private Product product;
	
	@NotNull
	private Integer storeId;
	
	@NotNull
	@Min(value = 1, message = "No of quantity cannot be less than 1")
	private Integer quantity;

	public ProductInStore(Product product, @NotNull Integer storeId,
			@NotNull @Min(value = 1, message = "No of quantity cannot be less than 1") Integer quantity) {
		this.product = product;
		this.storeId = storeId;
		this.quantity = quantity;
	}
	
	
}
