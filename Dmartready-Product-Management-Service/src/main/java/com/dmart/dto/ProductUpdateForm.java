package com.dmart.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateForm {
	@NotNull
	private Integer productId;
	@NotBlank
	private String newName;
	private Integer categoryId;
	@Min(value = 0, message = "Stocks cannot be less than 0")
	private Long totalStocks;
}
