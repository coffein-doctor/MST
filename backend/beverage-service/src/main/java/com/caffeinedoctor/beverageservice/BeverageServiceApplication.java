package com.caffeinedoctor.beverageservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BeverageServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeverageServiceApplication.class, args);
	}

}
