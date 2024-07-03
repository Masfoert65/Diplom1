package com.example.retailstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages={"com.example.retailstore"})
@EnableJpaRepositories(basePackages = "com.example.retailstore.repositories")
public class RetailStoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(RetailStoreApplication.class, args);
    }
}