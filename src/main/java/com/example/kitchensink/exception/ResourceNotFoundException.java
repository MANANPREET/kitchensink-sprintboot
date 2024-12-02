package com.example.kitchensink.exception;

/**
 * ResourceNotFoundException is a custom exception class that extends RuntimeException.
 * This exception is thrown when a requested resource is not found in the application.
 * It is typically used to indicate situations like missing data or unavailable resources.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructor for ResourceNotFoundException.
     *
     * @param message The error message to be associated with the exception.
     */
    public ResourceNotFoundException(String message) {
        super(message); // Pass the message to the parent RuntimeException class
    }
}
