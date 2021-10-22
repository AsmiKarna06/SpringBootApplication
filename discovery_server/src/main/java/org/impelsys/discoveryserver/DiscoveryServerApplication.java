package org.impelsys.discoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableEurekaServer	//holds all the services of the clients service application
@Slf4j
public class DiscoveryServerApplication {

	public static void main(String[] args) {
		log.info("Starting Service Discovery Server");
		System.out.println("in main");
		SpringApplication.run(DiscoveryServerApplication.class, args);
		
	}
}
