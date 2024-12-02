package com.example.kitchensink.controller;

import com.example.kitchensink.model.Member;
import com.example.kitchensink.model.Role;
import com.example.kitchensink.model.UserEntity;
import com.example.kitchensink.security.SecurityUtil;
import com.example.kitchensink.service.MemberService;
import com.example.kitchensink.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberControllerTest {

    @InjectMocks
    private MemberController memberController; // Controller to be tested

    @Mock
    private MemberService memberService; // Mocked MemberService

    @Mock
    private UserService userService; // Mocked UserService

    @Mock
    private Model model; // Mocked Model

    @Mock
    private SecurityContext securityContext; // Mocked SecurityContext

    @Mock
    private Authentication authentication; // Mocked Authentication
    @Mock
    private Role role;
    @Mock
    private UserEntity user;

    private Member member; // A test member entity

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks only once

        // Create a mock Role object (do not redeclare it later)
        role = mock(Role.class);
        when(role.getName()).thenReturn("USER");

        // Mock the user's roles
        when(user.getRoles()).thenReturn(List.of(role));

        // Mock the behavior for finding the user by username
        when(userService.findByUsername("testUser")).thenReturn(user);

        // Mock the Authentication object and SecurityContext
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("testUser");

        // Set the mocked SecurityContext in the SecurityContextHolder
        SecurityContextHolder.setContext(securityContext);

        // Create a sample member
        member = new Member();
        member.setId("1");
        member.setName("Test Member");
    }


    // Test the listMembers method
    @Test
    void testListMembers() {
        // Mock the userService to return the mocked user
        when(userService.findByUsername("testUser")).thenReturn(user);

        // Mock the memberService to return a list of members
        Member mockMember = new Member();
        mockMember.setId("1");
        mockMember.setName("Test Member");
        when(memberService.getAllUsers()).thenReturn(List.of(mockMember));  // Return a list with the mock member

        // Call the controller method
        String viewName = memberController.listMembers(model);

        // Check the view name
        assertEquals("index", viewName, "Expected index view name");

        // Use ArgumentCaptor to capture the actual argument passed to model.addAttribute("members", ...)
        ArgumentCaptor<List> membersCaptor = ArgumentCaptor.forClass(List.class);
        verify(model).addAttribute(eq("members"), membersCaptor.capture());

        // Assert that the list contains the expected member
        List<Member> capturedMembers = membersCaptor.getValue();
        assertNotNull(capturedMembers, "Captured members list should not be null");
        assertEquals(1, capturedMembers.size(), "Captured members list should contain one member");
        assertEquals("1", capturedMembers.get(0).getId(), "Expected member ID to be '1'");
        assertEquals("Test Member", capturedMembers.get(0).getName(), "Expected member name to be 'Test Member'");

        // Verify that the role name is added to the model
        verify(model).addAttribute("roleName", "USER"); // Verify the role name was added to the model
    }



    // Test showCreateForm method (for creating a new member)
    @Test
    void testShowCreateForm() {
        // Create an ArgumentCaptor for Member
        ArgumentCaptor<Member> memberCaptor = ArgumentCaptor.forClass(Member.class);

        // Call the method in the controller
        String viewName = memberController.showCreateForm(model);

        // Ensure the view name is correct
        assertEquals("user-form", viewName, "Expected user-form view name");

        // Capture the argument passed to model.addAttribute("member", ...)
        verify(model).addAttribute(eq("member"), memberCaptor.capture());

        // Get the captured Member object
        Member capturedMember = memberCaptor.getValue();

        // Assert that the captured Member is an instance of the correct class
        assertNotNull(capturedMember, "Captured member should not be null");

        // Assert that it is a new Member object (no properties set, empty object)
        assertNull(capturedMember.getId(), "New member should not have an ID");
        assertNull(capturedMember.getName(), "New member should not have a name");
        // Add other assertions for any fields in Member that you expect to be empty initially
    }

    // Test createMember method (for creating a new member)
    @Test
    void testCreateMember() {
        when(memberService.saveUser(any(Member.class))).thenReturn(member);

        String viewName = memberController.createMember(member);
        assertEquals("redirect:/", viewName, "Expected redirect to home page after member creation");

        verify(memberService).saveUser(member); // Ensure saveUser method was called
    }

    // Test showMemberDetails method
    @Test
    void testShowMemberDetails() {
        when(memberService.getUserById(anyString())).thenReturn(member); // Mock fetching member by ID

        String viewName = memberController.showMemberDetails("1", model);
        assertEquals("user-details", viewName, "Expected user-details view");

        verify(model).addAttribute("member", member); // Verify that member is added to the model
    }

    // Test the editMemberForm method
    @Test
    void testEditMemberForm() {
        when(memberService.getUserById(anyString())).thenReturn(member); // Mock fetching member by ID

        String viewName = memberController.editMemberForm("1", model);
        assertEquals("members-edit", viewName, "Expected members-edit view");

        verify(model).addAttribute("member", member); // Verify the member is passed to the model
    }

    // Test updateMember method
    @Test
    void testUpdateMember() throws IllegalAccessException {
        // Mock the updateMember method to do nothing (since it returns void)
        doNothing().when(memberService).updateMember(any(Member.class)); // Use doNothing() for void methods

        // Call the method in the controller
        String viewName = memberController.updateMember("1", member, model);

        // Ensure the redirect happens correctly
        assertEquals("redirect:/member", viewName, "Expected redirect to member list after update");

        // Verify that updateMember was called with the member object
        verify(memberService).updateMember(member); // Ensure updateMember was called
    }

    // Test deleteClub method (delete member)
    @Test
    void testDeleteClub() {
        doNothing().when(memberService).deleteUser(anyString()); // Mock deleting member

        String viewName = memberController.deleteClub("1");
        assertEquals("redirect:/member", viewName, "Expected redirect to member list after deletion");

        verify(memberService).deleteUser("1"); // Ensure deleteUser method was called
    }

    // Test error handling route
    @Test
    void testHandleError() {
        String viewName = memberController.handleError();
        assertEquals("index", viewName, "Expected index view on error");
    }
}

