package com.example.kitchensink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for the Spring Boot application.
 * This class serves as the entry point for starting the Spring Boot application.
 */
@SpringBootApplication // Indicates this is a Spring Boot application
public class WebApplication {

    /**
     * The main method which serves as the entry point for the Spring Boot application.
     * It starts the Spring Boot application by calling SpringApplication.run().
     *
     * @param args Command line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args); // Launches the Spring Boot application
    }
}
