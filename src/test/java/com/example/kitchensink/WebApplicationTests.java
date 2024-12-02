package com.example.kitchensink;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test class for WebApplication.
 * It ensures that the Spring Boot application context loads properly.
 */
@SpringBootTest // This annotation tells Spring Boot to look for the main configuration class and start the application context
public class WebApplicationTests {

    /**
     * This test ensures that the Spring application context loads correctly.
     * It will pass if the application context starts without any issues.
     */
    @Test
    void contextLoads() {
        // If the application context fails to load, the test will fail automatically.
        // No need for any assertions, Spring Boot does the work of testing the context loading.
    }
}
