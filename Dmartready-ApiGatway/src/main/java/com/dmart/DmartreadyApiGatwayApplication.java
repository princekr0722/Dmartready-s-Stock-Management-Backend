package com.dmart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DmartreadyApiGatwayApplication {

	public static void main(String[] args) {
		SpringApplication.run(DmartreadyApiGatwayApplication.class, args);
	}

}
