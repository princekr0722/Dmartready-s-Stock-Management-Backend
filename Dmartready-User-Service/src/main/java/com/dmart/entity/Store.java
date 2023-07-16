package com.dmart.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Store {
//	@JsonProperty(access = Access.READ_ONLY)
	private Integer id;
//	@JsonProperty(access = Access.READ_ONLY)
	private Integer storeManagerId;
	@NotNull
	private Address address;
}
