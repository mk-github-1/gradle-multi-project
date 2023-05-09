package com.example.applicationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.transaction.annotation.EnableTransactionManagement;

// @EnableTransactionManagement
@SpringBootApplication(scanBasePackages = {"com.example.applicationservice", "com.example.infrastructure", "com.example.domainservice", "com.example.domainmodel"})
public class ApplicationService {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationService.class, args);		
	}
}
