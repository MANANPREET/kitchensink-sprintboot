package com.example.kitchensink.repository;

import com.example.kitchensink.model.UserEntity;
import com.example.kitchensink.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for the UserRepository.
 * This test ensures that the methods in the UserRepository work as expected, including finding users by email and username.
 */
@DataMongoTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private UserEntity user;
    private Role role;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();  // Clear any existing data before each test
        roleRepository.deleteAll();

        role = new Role("1", "Admin");
        roleRepository.save(role);  // Save a sample role

        user = new UserEntity("1", "john_doe", "john.doe@example.com", "password123", List.of(role));
        userRepository.save(user);  // Save a sample user with a role
    }

    /**
     * Test the `findByEmail` method to retrieve a user by their email.
     */
    @Test
    void testFindByEmail() {
        // Act
        UserEntity foundUser = userRepository.findByEmail("john.doe@example.com");

        // Assert
        assertNotNull(foundUser, "User should be found by email.");
        assertEquals("john.doe@example.com", foundUser.getEmail(), "The email should match.");
    }

    /**
     * Test the `findByUsername` method to retrieve a user by their username.
     */
    @Test
    void testFindByUsername() {
        // Act
        UserEntity foundUser = userRepository.findByUsername("john_doe");

        // Assert
        assertNotNull(foundUser, "User should be found by username.");
        assertEquals("john_doe", foundUser.getUsername(), "The username should match.");
    }

    /**
     * Test the `findFirstByUsername` method to retrieve the first user with a given username.
     */
    @Test
    void testFindFirstByUsername() {
        // Act
        UserEntity foundUser = userRepository.findFirstByUsername("john_doe");

        // Assert
        assertNotNull(foundUser, "User should be found by username.");
        assertEquals("john_doe", foundUser.getUsername(), "The username should match.");
    }
}
