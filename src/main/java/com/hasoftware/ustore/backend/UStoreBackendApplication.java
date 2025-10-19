package com.hasoftware.ustore.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UStoreBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(UStoreBackendApplication.class, args);
    }
}
