package com.example.kitchensink.service;

import com.example.kitchensink.dto.RegistrationDto;
import com.example.kitchensink.model.UserEntity;

/**
 * Interface defining the contract for UserService, which handles user-related operations like registration.
 */
public interface UserService {

    /**
     * Saves a new user based on the registration data.
     *
     * @param registrationDto The registration data.
     */
    void saveUser(RegistrationDto registrationDto);

    /**
     * Finds a user by their email.
     *
     * @param email The email of the user.
     * @return The user associated with the given email.
     */
    UserEntity findByEmail(String email);

    /**
     * Finds a user by their username.
     *
     * @param username The username of the user.
     * @return The user associated with the given username.
     */
    UserEntity findByUsername(String username);
}
