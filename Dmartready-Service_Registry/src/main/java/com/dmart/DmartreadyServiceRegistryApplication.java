package com.dmart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DmartreadyServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DmartreadyServiceRegistryApplication.class, args);
	}

}
