package com.example.kitchensink.exception;

import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * GlobalExceptionHandler is a centralized exception handler for the application.
 * It intercepts various exceptions and returns appropriate HTTP responses.
 * The @ControllerAdvice annotation allows this class to handle exceptions globally for all controllers.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles ResourceNotFoundException.
     * This exception is thrown when a requested resource is not found.
     *
     * @param ex The exception instance
     * @return ResponseEntity with a 404 NOT FOUND status and the exception message in the response body
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
        // Responds with 404 and the error message from the exception
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Handles ValidationException.
     * This exception is thrown when a validation error occurs (e.g., invalid data).
     *
     * @param ex The exception instance
     * @return ResponseEntity with a 404 NOT FOUND status and the exception message in the response body
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handleValidationException(ValidationException ex) {
        // Responds with 404 and the validation error message from the exception
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Handles MethodArgumentNotValidException.
     * This exception is triggered when the method arguments (e.g., request body) fail validation.
     *
     * @param ex The exception instance
     * @return ResponseEntity with a 400 BAD REQUEST status and a map of field validation errors
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // Map to hold field errors with their corresponding error messages
        Map<String, String> errors = new HashMap<>();

        // Iterate through the list of validation errors and collect field names and error messages
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        // Return a 400 BAD REQUEST response with the map of validation errors
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
