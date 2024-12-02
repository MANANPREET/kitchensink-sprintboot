package com.example.kitchensink.repository;

import com.example.kitchensink.model.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * The UserRepository interface extends MongoRepository to provide CRUD operations on the UserEntity collection in MongoDB.
 * It contains custom query methods to find users by email, username, or get the first user with a given username.
 */
public interface UserRepository extends MongoRepository<UserEntity, String> {

    /**
     * Finds a user by their email address.
     * This method is commonly used for user authentication and profile retrieval by email.
     *
     * @param email The email address of the user
     * @return The user with the given email
     */
    UserEntity findByEmail(String email);

    /**
     * Finds a user by their username.
     * This method is typically used to retrieve a user by their unique username.
     *
     * @param userName The username of the user
     * @return The user with the given username
     */
    UserEntity findByUsername(String userName);

    /**
     * Finds the first user with a given username.
     * This method is used to fetch the first matching user when multiple users may have the same username.
     *
     * @param username The username of the user
     * @return The first user with the given username
     */
    UserEntity findFirstByUsername(String username);
}
