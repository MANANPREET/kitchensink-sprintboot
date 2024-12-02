package com.example.kitchensink.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Member class.
 * These tests verify that the validation constraints on the Member class work as expected.
 */
class MemberTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        // Initialize the validator before each test
        validator = new LocalValidatorFactoryBean();
        ((LocalValidatorFactoryBean) validator).afterPropertiesSet();
    }

    /**
     * Test the validation of a valid Member object.
     * This ensures that all constraints are met (name, email, and phone).
     */
    @Test
    void testValidMember() {
        // Arrange
        Member member = new Member();
        member.setId("123");
        member.setName("John Doe");
        member.setEmail("john.doe@example.com");
        member.setPhone("1234567890");

        // Act
        BindingResult bindingResult = new org.springframework.validation.BeanPropertyBindingResult(member, "member");
        validator.validate(member, bindingResult);

        // Assert
        assertFalse(bindingResult.hasErrors(), "The valid member should not have validation errors.");
    }

    /**
     * Test validation for missing name.
     * This ensures that a member without a name will fail validation.
     */
    @Test
    void testMemberWithMissingName() {
        // Arrange
        Member member = new Member();
        member.setId("123");
        member.setName("");
        member.setEmail("john.doe@example.com");
        member.setPhone("1234567890");

        // Act
        BindingResult bindingResult = new org.springframework.validation.BeanPropertyBindingResult(member, "member");
        validator.validate(member, bindingResult);

        // Assert
        assertTrue(bindingResult.hasErrors(), "The member with a missing name should have validation errors.");
        assertEquals("Name is mandatory", bindingResult.getFieldError("name").getDefaultMessage());
    }

    /**
     * Test validation for invalid email format.
     * This ensures that a member with an invalid email will fail validation.
     */
    @Test
    void testMemberWithInvalidEmail() {
        // Arrange
        Member member = new Member();
        member.setId("123");
        member.setName("John Doe");
        member.setEmail("invalid-email");
        member.setPhone("1234567890");

        // Act
        BindingResult bindingResult = new org.springframework.validation.BeanPropertyBindingResult(member, "member");
        validator.validate(member, bindingResult);

        // Assert
        assertTrue(bindingResult.hasErrors(), "The member with an invalid email should have validation errors.");
        assertEquals("Invalid email format", bindingResult.getFieldError("email").getDefaultMessage());
    }

    /**
     * Test validation for missing phone number.
     * This ensures that a member without a phone number will fail validation.
     */
    @Test
    void testMemberWithMissingPhone() {
        // Arrange
        Member member = new Member();
        member.setId("123");
        member.setName("John Doe");
        member.setEmail("john.doe@example.com");
        member.setPhone("");

        // Act
        BindingResult bindingResult = new org.springframework.validation.BeanPropertyBindingResult(member, "member");
        validator.validate(member, bindingResult);

        // Assert
        assertTrue(bindingResult.hasErrors(), "The member with a missing phone should have validation errors.");
        assertEquals("Phone is mandatory", bindingResult.getFieldError("phone").getDefaultMessage());
    }
}
