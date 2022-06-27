package com.fis.Sprint_4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class Sprint4Application {
    public static void main(String[] args) {
        SpringApplication.run(Sprint4Application.class, args);
    }

}
