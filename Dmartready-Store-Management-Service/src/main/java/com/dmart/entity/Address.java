package com.dmart.entity;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty(access = Access.READ_ONLY)
	private Integer id;
	
	@Column(nullable = false)
	@NotNull
	private String country;
	
	@Column(nullable = false)
	@NotNull
	private String state;
	
	@Column(nullable = false)
	@NotNull
	private String city;
	
	@Column(nullable = false)
	@NotNull
	private String pinCode;
	
	@Column(nullable = false)
	@NotNull
	private String addressLine1;
	
	@OneToOne
	@JoinColumn(name="store_id")
	@JsonIgnore
	private Store store;

	@Override
	public String toString() {
		return "Address [id=" + id + ", country=" + country + ", state=" + state + ", city=" + city + ", pinCode="
				+ pinCode + ", addressLine1=" + addressLine1 + ", store=" + store + "]";
	}
	
	
}
