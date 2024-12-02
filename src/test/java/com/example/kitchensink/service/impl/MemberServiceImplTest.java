package com.example.kitchensink.service.impl;

import com.example.kitchensink.model.Member;
import com.example.kitchensink.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class) // Add this annotation to enable Mockito extension
public class MemberServiceImplTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberServiceImpl memberService;

    private Member member;

    @BeforeEach
    void setUp() {
        // Create a new Member and set its properties
        member = new Member();
        member.setName("John Doe");
        member.setEmail("john.doe@example.com");
        member.setPhone("1234567890");
    }

    @Test
    void saveUser_ShouldSaveMember() {
        // Arrange: Mock the memberRepository to return the member when save() is called
        when(memberRepository.save(any(Member.class))).thenReturn(member);

        // Act: Call the saveUser method
        memberService.saveUser(member);

        // Assert: Verify that the save method was called on memberRepository
        verify(memberRepository).save(any(Member.class));
    }
}
