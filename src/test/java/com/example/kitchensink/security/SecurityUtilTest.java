package com.example.kitchensink.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SecurityUtilTest {

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetSessionUser_authenticatedUser() {
        // Mock an authenticated user
        when(authentication.getName()).thenReturn("testUser");

        // Mock the SecurityContext
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        // Test the getSessionUser method
        String result = SecurityUtil.getSessionUser();

        // Verify the result
        assertEquals("testUser", result);

        // Clean up the SecurityContext
        SecurityContextHolder.clearContext();
    }

    @Test
    void testGetSessionUser_anonymousUser_withAuthorities() {
        // Mock an anonymous user with authorities (fixing the IllegalArgumentException)
        Authentication anonymousAuthentication = new AnonymousAuthenticationToken(
                "key", "anonymous", List.of(() -> "ROLE_USER"));  // Provide at least one authority

        // Mock the SecurityContext
        when(securityContext.getAuthentication()).thenReturn(anonymousAuthentication);
        SecurityContextHolder.setContext(securityContext);

        // Test the getSessionUser method
        String result = SecurityUtil.getSessionUser();

        // Verify the result is null because the user is anonymous
        assertNull(result);

        // Clean up the SecurityContext
        SecurityContextHolder.clearContext();
    }
}
