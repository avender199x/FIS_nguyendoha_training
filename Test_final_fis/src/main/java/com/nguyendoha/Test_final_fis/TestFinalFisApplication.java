package com.nguyendoha.Test_final_fis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class TestFinalFisApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestFinalFisApplication.class, args);
	}

}
