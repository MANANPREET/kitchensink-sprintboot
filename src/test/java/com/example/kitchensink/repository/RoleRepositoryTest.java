package com.example.kitchensink.repository;

import com.example.kitchensink.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for the RoleRepository.
 * This test ensures that the methods in the RoleRepository work as expected, including finding roles by their name.
 */
@DataMongoTest  // This annotation tells Spring Boot to load only the Mongo-related beans and tests repository functionality
class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    private Role role;

    @BeforeEach
    void setUp() {
        roleRepository.deleteAll();  // Clear any existing data before each test
        role = new Role("1", "Admin");
        roleRepository.save(role);  // Save a sample role to the repository
    }

    /**
     * Test the `findByName` method to retrieve a role by its name.
     */
    @Test
    void testFindByName() {
        // Act
        Role foundRole = roleRepository.findByName("Admin");

        // Assert
        assertNotNull(foundRole, "Role should be found by name.");
        assertEquals("Admin", foundRole.getName(), "The role name should match.");
    }
}
