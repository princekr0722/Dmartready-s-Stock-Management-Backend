package com.dmart.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreationForm {
	@NotBlank
	private String produtName;
	private Integer categoryId;
	
	@Min(value = 0, message = "Stock cannot be less than 0")
	private Long totalStocks;
}
