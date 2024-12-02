package com.example.kitchensink.service.impl;

import com.example.kitchensink.exception.ResourceNotFoundException;
import com.example.kitchensink.model.Member;
import com.example.kitchensink.repository.MemberRepository;
import com.example.kitchensink.service.MemberService;
import jakarta.validation.ValidationException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the MemberService interface, providing operations related to members.
 */
@Service
public class MemberServiceImpl implements MemberService {

    private MemberRepository memberRepository;
    private MongoTemplate mongoTemplate;

    /**
     * Constructor to initialize dependencies for the MemberServiceImpl class.
     *
     * @param memberRepository The repository for Member entities.
     * @param mongoTemplate The MongoTemplate used for advanced database queries.
     */
    public MemberServiceImpl(MemberRepository memberRepository, MongoTemplate mongoTemplate) {
        this.memberRepository = memberRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Member> getAllUsers() {
        return memberRepository.findAllByOrderByNameAsc();
    }

    @Override
    public Member getUserById(String id) {
        return memberRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public Member saveUser(Member member) {
        // Validate the member's details before saving
        validateMember(member);
        return memberRepository.save(member);
    }

    /**
     * Validates the member's details to ensure uniqueness of email and phone.
     *
     * @param member The member to be validated.
     * @throws ValidationException if email or phone is already in use.
     */
    private void validateMember(Member member) throws ValidationException {
        if (emailAlreadyExists(member.getEmail())) {
            throw new ValidationException("Unique Email Violation");
        }
        if (phoneAlreadyExists(member.getPhone())) {
            throw new ValidationException("Unique Phone Violation");
        }
    }

    /**
     * Checks if the provided email is already in use by another member.
     *
     * @param email The email to be checked.
     * @return true if email exists, false otherwise.
     */
    private boolean emailAlreadyExists(String email) {
        return memberRepository.findByEmail(email) != null;
    }

    /**
     * Checks if the provided phone number is already in use by another member.
     *
     * @param phone The phone number to be checked.
     * @return true if phone exists, false otherwise.
     */
    private boolean phoneAlreadyExists(String phone) {
        return memberRepository.findByPhone(phone) != null;
    }

    @Override
    public void deleteUser(String id) {
        memberRepository.deleteById(id);
    }

    @Override
    public void updateMember(Member member) throws IllegalAccessException {
        if (member.getId() == null) {
            throw new IllegalAccessException("ID cannot be null");
        }

        Query query = new Query(Criteria.where("_id").is(member.getId()));
        Update update = new Update()
                .set("email", member.getEmail())
                .set("phone", member.getPhone())
                .set("name", member.getName());

        mongoTemplate.updateFirst(query, update, Member.class);
    }
}
