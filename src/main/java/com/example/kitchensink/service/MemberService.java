package com.example.kitchensink.service;

import com.example.kitchensink.model.Member;

import java.util.List;

public interface MemberService {
     Member saveUser(Member member);
     void deleteUser(String id);
     List<Member> getAllUsers();
     Member getUserById(String id);
     void updateMember(Member member) throws IllegalAccessException;
}
