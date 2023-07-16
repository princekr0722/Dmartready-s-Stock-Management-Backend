package com.dmart.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
	@JsonProperty(access = Access.READ_ONLY)
	private Integer id;

	@NotBlank
	private String country;

	@NotBlank
	private String state;

	@NotBlank
	private String city;

	@NotBlank
	private String pinCode;

	@NotBlank
	private String addressLine1;

	public Address(String country, String state, String city, String pinCode, String addressLine1) {
		this.country = country;
		this.state = state;
		this.city = city;
		this.pinCode = pinCode;
		this.addressLine1 = addressLine1;
	}
}
