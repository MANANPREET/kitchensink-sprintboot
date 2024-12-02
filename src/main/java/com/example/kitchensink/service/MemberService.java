package com.example.kitchensink.service;

import com.example.kitchensink.model.Member;

import java.util.List;

/**
 * Interface defining the contract for MemberService, including CRUD operations for the Member entity.
 */
public interface MemberService {

     /**
      * Saves a new member to the database.
      *
      * @param member The member to be saved.
      * @return The saved member.
      */
     Member saveUser(Member member);

     /**
      * Deletes a member based on the given ID.
      *
      * @param id The ID of the member to be deleted.
      */
     void deleteUser(String id);

     /**
      * Retrieves all members from the database.
      *
      * @return A list of all members.
      */
     List<Member> getAllUsers();

     /**
      * Retrieves a member by their ID.
      *
      * @param id The ID of the member.
      * @return The member associated with the given ID.
      */
     Member getUserById(String id);

     /**
      * Updates an existing member's information.
      *
      * @param member The member object with updated details.
      * @throws IllegalAccessException if the member's ID is null or invalid.
      */
     void updateMember(Member member) throws IllegalAccessException;
}
