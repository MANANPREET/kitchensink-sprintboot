package com.example.kitchensink.repository;

import com.example.kitchensink.model.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for the MemberRepository.
 * This test ensures that the methods in the MemberRepository work as expected, including custom queries like findByEmail, findByPhone, and findAllByOrderByNameAsc.
 */
@DataMongoTest  // This annotation tells Spring Boot to load only the Mongo-related beans and tests repository functionality
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    private Member member;

    @BeforeEach
    void setUp() {
        memberRepository.deleteAll();  // Clear any existing data before each test
        member = new Member();
        member.setName("John Doe");
        member.setEmail("john.doe@example.com");
        member.setPhone("1234567890");
        memberRepository.save(member);  // Save a sample member to the repository
    }

    /**
     * Test that the `findAllByOrderByNameAsc` method returns members in ascending order by name.
     */
    @Test
    void testFindAllByOrderByNameAsc() {
        // Arrange
        Member member2 = new Member();
        member2.setName("Alice Smith");
        member2.setEmail("alice.smith@example.com");
        memberRepository.save(member2);

        // Act
        List<Member> members = memberRepository.findAllByOrderByNameAsc();

        // Assert
        assertEquals(2, members.size(), "The list should contain two members.");
        assertEquals("Alice Smith", members.get(0).getName(), "The first member should be Alice Smith.");
        assertEquals("John Doe", members.get(1).getName(), "The second member should be John Doe.");
    }

    /**
     * Test the `findByEmail` method to retrieve a member by their email.
     */
    @Test
    void testFindByEmail() {
        // Act
        Member foundMember = memberRepository.findByEmail("john.doe@example.com");

        // Assert
        assertNotNull(foundMember, "Member should be found by email.");
        assertEquals("john.doe@example.com", foundMember.getEmail(), "The email should match.");
    }

    /**
     * Test the `findByPhone` method to retrieve a member by their phone number.
     */
    @Test
    void testFindByPhone() {
        // Act
        Member foundMember = memberRepository.findByPhone("1234567890");

        // Assert
        assertNotNull(foundMember, "Member should be found by phone number.");
        assertEquals("1234567890", foundMember.getPhone(), "The phone number should match.");
    }

    /**
     * Test the `findByName` method to retrieve a member by their name.
     */
    @Test
    void testFindByName() {
        // Act
        Member foundMember = memberRepository.findByName("John Doe");

        // Assert
        assertNotNull(foundMember, "Member should be found by name.");
        assertEquals("John Doe", foundMember.getName(), "The name should match.");
    }
}
