package com.car.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CarServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarServicesApplication.class, args);
	}

}
