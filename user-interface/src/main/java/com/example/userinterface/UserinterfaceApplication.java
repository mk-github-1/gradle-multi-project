package com.example.userinterface;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.userinterface", "com.example.applicationservice", "com.example.infrastructure", "com.example.domainservice", "com.example.domainmodel"})
public class UserinterfaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserinterfaceApplication.class, args);
	}

}
