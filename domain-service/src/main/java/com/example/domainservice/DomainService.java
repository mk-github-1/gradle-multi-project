package com.example.domainservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.infrastructure", "com.example.domainservice", "com.example.domainmodel"})
public class DomainService {

	public static void main(String[] args) {
		SpringApplication.run(DomainService.class, args);
	}

}
