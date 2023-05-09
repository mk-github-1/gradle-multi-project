package com.example.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.infrastructure", "com.example.domainservice", "com.example.domainmodel"})
public class Infrastructure {

	public static void main(String[] args) {
		SpringApplication.run(Infrastructure.class, args);
	}

}
