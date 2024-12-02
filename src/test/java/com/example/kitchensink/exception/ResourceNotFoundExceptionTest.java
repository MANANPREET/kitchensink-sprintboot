package com.example.kitchensink.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the ResourceNotFoundException class.
 * These tests check that the exception is correctly instantiated and the message is properly passed.
 */
class ResourceNotFoundExceptionTest {

    /**
     * Test that the exception can be created with a given error message.
     * This ensures that the constructor correctly passes the message to the superclass.
     */
    @Test
    void testExceptionMessage() {
        // Arrange
        String errorMessage = "Resource not found";

        // Act
        ResourceNotFoundException exception = new ResourceNotFoundException(errorMessage);

        // Assert
        // Check that the message passed to the exception is correctly set
        assertEquals(errorMessage, exception.getMessage(), "The exception message should match the input message.");
    }

    /**
     * Test that the exception is an instance of RuntimeException.
     * Since ResourceNotFoundException extends RuntimeException, it should be treated as such.
     */
    @Test
    void testExceptionInheritance() {
        // Arrange
        String errorMessage = "Resource not found";

        // Act
        ResourceNotFoundException exception = new ResourceNotFoundException(errorMessage);

        // Assert
        // Check that the exception is an instance of RuntimeException
        assertTrue(exception instanceof RuntimeException, "ResourceNotFoundException should be a subclass of RuntimeException.");
    }

    /**
     * Test that the exception can be thrown and caught properly.
     * This test verifies that the exception behaves as expected when used in code.
     */
    @Test
    void testExceptionThrowing() {
        // Arrange
        String errorMessage = "Resource not found";

        // Act & Assert
        // Ensure that throwing the exception results in it being caught and the message is as expected
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            throw new ResourceNotFoundException(errorMessage);
        });

        // Assert that the exception message is correctly passed through
        assertEquals(errorMessage, exception.getMessage(), "The exception message should be properly propagated.");
    }
}
