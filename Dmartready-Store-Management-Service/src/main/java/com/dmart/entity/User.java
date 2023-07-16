package com.dmart.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private Integer id;

	private String username;

	private String password;

	private String role;

	private String fullname;
	private Integer storeId;
}
