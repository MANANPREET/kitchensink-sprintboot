package com.example.kitchensink.repository;

import com.example.kitchensink.model.Member;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * The MemberRepository interface extends MongoRepository to provide CRUD operations on the Member collection in MongoDB.
 * It contains custom query methods to fetch members in a specific order and by specific fields like email, phone, and name.
 */
public interface MemberRepository extends MongoRepository<Member, String> {

    /**
     * Finds all members sorted by their name in ascending order.
     * This method is used to get all members ordered alphabetically by their name.
     *
     * @return List of members sorted by name in ascending order
     */
    List<Member> findAllByOrderByNameAsc(); // Ascending order by name

    /**
     * Finds a member by their email address.
     * This method is used to retrieve a member by their email, typically used for login or profile retrieval.
     *
     * @param email The email address of the member
     * @return The member with the given email
     */
    Member findByEmail(String email);

    /**
     * Finds a member by their phone number.
     * This method is used to retrieve a member by their phone number.
     *
     * @param phone The phone number of the member
     * @return The member with the given phone number
     */
    Member findByPhone(String phone);

    /**
     * Finds a member by their name.
     * This method is used to retrieve a member by their name.
     *
     * @param name The name of the member
     * @return The member with the given name
     */
    Member findByName(String name);
}
