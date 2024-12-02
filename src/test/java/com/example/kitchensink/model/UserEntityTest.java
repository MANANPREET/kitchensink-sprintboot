package com.example.kitchensink.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the UserEntity class.
 * These tests ensure that the UserEntity class behaves as expected, including its properties and methods.
 */
class UserEntityTest {

    private UserEntity userEntity;

    /**
     * Setup method to create a new instance of UserEntity before each test.
     */
    @BeforeEach
    void setUp() {
        userEntity = new UserEntity();  // Create a new instance of UserEntity before each test
    }

    /**
     * Test the constructor with parameters.
     * This ensures that the UserEntity can be initialized with an ID, username, email, password, and roles.
     */
    @Test
    void testConstructorWithParameters() {
        // Arrange
        String id = "1";
        String username = "testUser";
        String email = "testuser@example.com";
        String password = "password123";
        Role role = new Role("1", "Admin");  // A mock role
        List<Role> roles = List.of(role);   // Assign a single role to the user

        // Act
        UserEntity user = new UserEntity(id, username, email, password, roles);

        // Assert
        assertEquals(id, user.getId(), "The ID should be set correctly.");
        assertEquals(username, user.getUsername(), "The username should be set correctly.");
        assertEquals(email, user.getEmail(), "The email should be set correctly.");
        assertEquals(password, user.getPassword(), "The password should be set correctly.");
        assertEquals(roles, user.getRoles(), "The roles should be set correctly.");
    }

    /**
     * Test the default constructor and setter methods.
     * This ensures that the setters and no-args constructor work as expected.
     */
    @Test
    void testDefaultConstructorAndSetters() {
        // Arrange
        String id = "2";
        String username = "newUser";
        String email = "newuser@example.com";
        String password = "newpassword456";
        Role role = new Role("2", "User");  // A mock role
        List<Role> roles = List.of(role);   // Assign a role to the user

        // Act
        userEntity.setId(id);
        userEntity.setUsername(username);
        userEntity.setEmail(email);
        userEntity.setPassword(password);
        userEntity.setRoles(roles);

        // Assert
        assertEquals(id, userEntity.getId(), "The ID should be set correctly.");
        assertEquals(username, userEntity.getUsername(), "The username should be set correctly.");
        assertEquals(email, userEntity.getEmail(), "The email should be set correctly.");
        assertEquals(password, userEntity.getPassword(), "The password should be set correctly.");
        assertEquals(roles, userEntity.getRoles(), "The roles should be set correctly.");
    }

    /**
     * Test the `toString()` method.
     * This ensures that the `toString()` method generates the correct string representation of the UserEntity.
     */
    @Test
    void testToString() {
        // Arrange
        Role role = new Role("3", "Admin");
        List<Role> roles = List.of(role);
        UserEntity user = new UserEntity("3", "johnDoe", "john.doe@example.com", "password789", roles);

        // Act
        String userString = user.toString();

        // Assert
        assertTrue(userString.contains("id='3'"), "The toString method should include the user ID.");
        assertTrue(userString.contains("username='johnDoe'"), "The toString method should include the username.");
        assertTrue(userString.contains("email='john.doe@example.com'"), "The toString method should include the email.");
        assertTrue(userString.contains("roles=[Role(id=3, name=Admin)]"), "The toString method should include the roles.");
    }

    /**
     * Test the default roles list.
     * This ensures that when a new UserEntity is created, the roles list is initialized to an empty list.
     */
    @Test
    void testDefaultRolesList() {
        // Act
        UserEntity user = new UserEntity();

        // Assert
        assertNotNull(user.getRoles(), "The roles list should not be null.");
        assertTrue(user.getRoles().isEmpty(), "The roles list should be empty by default.");
    }

    /**
     * Test the behavior of the @DBRef annotation.
     * This is a simple test to verify that the @DBRef works as expected for referencing Role documents.
     */
    @Test
    void testDBRefAnnotation() {
        // Arrange
        Role role = mock(Role.class);  // Mock a Role object
        List<Role> roles = List.of(role);
        UserEntity user = new UserEntity("4", "alice", "alice@example.com", "password101", roles);

        // Act & Assert
        assertEquals(roles, user.getRoles(), "The roles should reference the expected Role object.");
    }
}
