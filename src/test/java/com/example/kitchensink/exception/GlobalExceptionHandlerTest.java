package com.example.kitchensink.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import jakarta.validation.ValidationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Mock
    private MethodArgumentNotValidException methodArgumentNotValidException;

    @Mock
    private ValidationException validationException;

    @Mock
    private ResourceNotFoundException resourceNotFoundException;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks
    }

    @Test
    void testHandleResourceNotFound() {
        // Arrange
        String errorMessage = "Resource not found";
        when(resourceNotFoundException.getMessage()).thenReturn(errorMessage);

        // Act
        ResponseEntity<String> response = globalExceptionHandler.handleResourceNotFound(resourceNotFoundException);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(errorMessage, response.getBody());
    }

    @Test
    void testHandleValidationException() {
        // Arrange
        String errorMessage = "Validation error";
        when(validationException.getMessage()).thenReturn(errorMessage);

        // Act
        ResponseEntity<String> response = globalExceptionHandler.handleValidationException(validationException);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(errorMessage, response.getBody());
    }

    @Test
    void testHandleMethodArgumentNotValidException() {
        // Arrange
        FieldError fieldError = new FieldError("user", "name", "Name is required");
        when(methodArgumentNotValidException.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getAllErrors()).thenReturn(Arrays.asList(fieldError));

        // Act
        ResponseEntity<Map<String, String>> response = globalExceptionHandler.handleValidationExceptions(methodArgumentNotValidException);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Map<String, String> expectedErrors = new HashMap<>();
        expectedErrors.put("name", "Name is required");
        assertEquals(expectedErrors, response.getBody());
    }
}
