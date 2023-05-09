package com.example.domainmodel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.domainmodel"})
public class DomainModel {

	public static void main(String[] args) {
		SpringApplication.run(DomainModel.class, args);
	}

}
