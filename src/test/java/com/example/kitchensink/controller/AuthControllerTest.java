package com.example.kitchensink.controller;

import com.example.kitchensink.dto.RegistrationDto;
import com.example.kitchensink.model.UserEntity;
import com.example.kitchensink.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private UserService userService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    private RegistrationDto registrationDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        registrationDto = new RegistrationDto();
        registrationDto.setEmail("test@example.com");
        registrationDto.setUsername("testuser");
        registrationDto.setPassword("password123");
    }

    // Test for /login endpoint
    @Test
    void testLoginPage() {
        String viewName = authController.loginPage();
        assertEquals("login", viewName, "Expected login view name");
    }

    // Test for /register endpoint, ensuring the registration form is returned
    @Test
    void testGetRegisterForm() {
        String viewName = authController.getRegisterForm(model);
        assertEquals("register", viewName, "Expected register view name");
        verify(model).addAttribute(eq("user"), any(RegistrationDto.class));
    }

    // Test when user tries to register but email already exists
    @Test
    void testRegister_EmailExists() {
        UserEntity existingUser = new UserEntity();
        existingUser.setEmail("test@example.com");

        when(userService.findByEmail(anyString())).thenReturn(existingUser);

        String result = authController.register(registrationDto, bindingResult, model);

        assertEquals("redirect:/register?fail", result, "Expected redirect to register with fail due to existing email");
    }

    // Test when user tries to register but username already exists
    @Test
    void testRegister_UsernameExists() {
        UserEntity existingUser = new UserEntity();
        existingUser.setUsername("testuser");

        when(userService.findByUsername(anyString())).thenReturn(existingUser);

        String result = authController.register(registrationDto, bindingResult, model);

        assertEquals("redirect:/register?fail", result, "Expected redirect to register with fail due to existing username");
    }

    // Test for successful registration with no errors and unique username/email
    @Test
    void testRegister_SuccessfulRegistration() {
        when(userService.findByEmail(anyString())).thenReturn(null); // No user found by email
        when(userService.findByUsername(anyString())).thenReturn(null); // No user found by username
        when(bindingResult.hasErrors()).thenReturn(false); // No validation errors

        String result = authController.register(registrationDto, bindingResult, model);

        assertEquals("redirect:/member?success", result, "Expected redirect to member page after successful registration");
        verify(userService).saveUser(any(RegistrationDto.class)); // Ensure saveUser method is called
    }

    // Test when there are validation errors on the registration form
    @Test
    void testRegister_WithValidationErrors() {
        when(bindingResult.hasErrors()).thenReturn(true); // Simulate validation errors

        String result = authController.register(registrationDto, bindingResult, model);

        assertEquals("register", result, "Expected to return to register page due to validation errors");
        verify(model).addAttribute(eq("user"), any(RegistrationDto.class)); // Ensure the model attribute is added
    }
}

