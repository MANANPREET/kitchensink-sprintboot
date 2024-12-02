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

@Service
public class MemberServiceImpl implements MemberService {
    private MemberRepository memberRepository;
    private MongoTemplate mongoTemplate;

    public MemberServiceImpl(MemberRepository memberRepository, MongoTemplate mongoTemplate) {
        this.memberRepository = memberRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public List<Member> getAllUsers() {
        return memberRepository.findAllByOrderByNameAsc();
    }
    public Member getUserById(String id) {
        return memberRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
    public Member saveUser(Member member) {
        //validation
        validateMember(member);
        return memberRepository.save(member);
    }

    private void validateMember(Member user) throws ValidationException {
        // Check the uniqueness of the email address
        if (emailAlreadyExists(user.getEmail())) {
            throw new ValidationException("Unique Email Violation");
        }
        if (phoneAlreadyExists(user.getPhone())) {
            throw new ValidationException("Unique Phone Violation");
        }
    }

    private boolean emailAlreadyExists(String email) {
        return memberRepository.findByEmail(email) != null;
    }
    private boolean phoneAlreadyExists(String phone) {
        return memberRepository.findByPhone(phone) != null;
    }

    public void deleteUser(String id) {
        memberRepository.deleteById(id);
    }

    @Override
    public void updateMember(Member member) throws IllegalAccessException {
        if(member.getId() == null) {
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
