package com.example.kitchensink.repository;

import com.example.kitchensink.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * The RoleRepository interface extends MongoRepository to provide CRUD operations on the Role collection in MongoDB.
 * It contains a custom query method to retrieve roles by their name.
 */
public interface RoleRepository extends MongoRepository<Role, String> {

    /**
     * Finds a role by its name.
     * This method is typically used to fetch a role based on its name for role-based access control (RBAC).
     *
     * @param name The name of the role
     * @return The role with the given name
     */
    Role findByName(String name);
}
