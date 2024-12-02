package com.example.kitchensink.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * The UserEntity class represents a user in the system.
 * It is mapped to a MongoDB collection called "users".
 * This class includes basic user properties like `username`, `email`, and `password`,
 * as well as a list of roles (`roles`) that are referenced using MongoDB's `@DBRef` annotation.
 * The Lombok annotations generate boilerplate code such as getters, setters, constructors, and `toString` method.
 */
@Getter  // Lombok annotation to generate getters for all fields
@Setter  // Lombok annotation to generate setters for all fields
@NoArgsConstructor  // Lombok annotation to generate a no-args constructor
@AllArgsConstructor  // Lombok annotation to generate an all-args constructor
@Document(collection = "users")  // MongoDB collection mapping for the UserEntity object
public class UserEntity {

    /**
     * The unique identifier for the user.
     * MongoDB uses String as the default type for IDs.
     */
    @Id
    private String id;

    /**
     * The username of the user.
     * This field is used to uniquely identify the user in the system.
     */
    private String username;

    /**
     * The email address of the user.
     * This field stores the email for communication purposes and for user authentication.
     */
    private String email;

    /**
     * The password of the user.
     * This field stores the hashed password for authentication purposes.
     */
    private String password;

    /**
     * The list of roles assigned to the user.
     * This is a reference to `Role` documents in MongoDB, which are linked to the user using the `@DBRef` annotation.
     */
    @DBRef  // MongoDB reference to the Role collection
    private List<Role> roles = new ArrayList<>();  // Defaulting to an empty list of roles

    /**
     * Custom `toString()` method to provide a string representation of the UserEntity.
     * This includes the user’s ID, username, email, password, and roles.
     */
    @Override
    public String toString() {
        return "UserEntity{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
