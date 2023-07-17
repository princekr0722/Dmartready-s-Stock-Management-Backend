package com.dmart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import com.dmart.config.security.CorsConfiguration;

@SpringBootApplication
@EnableEurekaClient
public class DmartreadyApiGatwayApplication {

	public static void main(String[] args) {
		SpringApplication.run(DmartreadyApiGatwayApplication.class, args);
	}

	@Bean
    CorsConfiguration corsConfigurationAll() {
        return new CorsConfiguration();
    }
}
