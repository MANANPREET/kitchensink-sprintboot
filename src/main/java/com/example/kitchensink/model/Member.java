package com.example.kitchensink.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Member is a model class that represents a member of the system.
 * It includes fields for the member's unique identifier, name, email, and phone.
 * The class is mapped to a MongoDB collection named "members" via the @Document annotation.
 */
@Document(collection = "members")
public class Member {

    /**
     * The unique identifier for the member.
     * This field is mapped to the "_id" field in the MongoDB collection.
     */
    @Id
    private String id;

    /**
     * The name of the member.
     * This field cannot be blank (validated using @NotBlank).
     */
    @NotBlank(message = "Name is mandatory")
    private String name;

    /**
     * The email address of the member.
     * This field cannot be blank and must be a valid email (validated using @NotBlank and @Email).
     */
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email format")
    private String email;

    /**
     * The phone number of the member.
     * This field cannot be blank (validated using @NotBlank).
     */
    @NotBlank(message = "Phone is mandatory")
    private String phone;

    // Getters and Setters

    /**
     * Gets the phone number of the member.
     *
     * @return the phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the member.
     *
     * @param phone the phone number to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the unique identifier (ID) of the member.
     *
     * @return the ID
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier (ID) of the member.
     *
     * @param id the ID to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the name of the member.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the member.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the email address of the member.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the member.
     *
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
