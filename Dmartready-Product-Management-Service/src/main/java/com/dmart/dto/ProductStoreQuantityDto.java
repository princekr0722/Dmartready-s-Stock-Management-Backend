package com.dmart.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductStoreQuantityDto {
	@NotNull
	private Integer productId;
	
	@NotNull
	@JsonProperty(access = Access.READ_ONLY)
	private Integer storeId;
	
	@NotNull
	@Min(value = 1, message ="Quantity cannot be less than 1")
	private Integer quantity;
}
