package org.impelsys.employeeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class EmployeeMicroServiceApplication {

	@Bean
	@LoadBalanced //client side load balancer
	public RestTemplate getRestestTemplate() {
		return new RestTemplate();	//RestTemplate allows to communiatebetween 2 or more webservices
	}
	public static void main(String[] args) {
		SpringApplication.run(EmployeeMicroServiceApplication.class, args);
	}

}
