package com.example.kitchensink.security;

import com.example.kitchensink.model.Role;
import com.example.kitchensink.model.UserEntity;
import com.example.kitchensink.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the CustomUserDetailsService class.
 * These tests verify the behavior of the loadUserByUsername method under different scenarios.
 */
public class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;  // Mocked UserRepository to simulate database interactions

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;  // Service being tested

    private UserEntity testUser;  // Test user for unit tests

    @BeforeEach
    public void setUp() {
        // Initialize mocks before each test
        MockitoAnnotations.openMocks(this);

        // Create a test user with basic details
        testUser = new UserEntity();
        testUser.setUsername("testuser");
        testUser.setPassword("testpassword");
        testUser.setEmail("testuser@example.com");

        // Create a mock role for the user
        Role role = new Role();
        role.setName("USER");
        testUser.setRoles(List.of(role));
    }

    /**
     * Test case to check if the user is found and returned with the correct roles.
     */
    @Test
    public void testLoadUserByUsername_UserFound() {
        // Arrange: Mock the repository to return the test user when a username is provided
        when(userRepository.findFirstByUsername("testuser")).thenReturn(testUser);

        // Act: Call the service to load the user
        var userDetails = customUserDetailsService.loadUserByUsername("testuser");

        // Assert: Verify that the correct user is returned with the correct username and roles
        assertEquals("testuser", userDetails.getUsername());
    }

    /**
     * Test case to check if an exception is thrown when the user is not found.
     */
    @Test
    public void testLoadUserByUsername_UserNotFound() {
        // Arrange: Mock the repository to return null for a non-existing user
        when(userRepository.findFirstByUsername("nonexistentuser")).thenReturn(null);

        // Act & Assert: Verify that UsernameNotFoundException is thrown
        assertThrows(UsernameNotFoundException.class, () -> customUserDetailsService.loadUserByUsername("nonexistentuser"));
    }
}
