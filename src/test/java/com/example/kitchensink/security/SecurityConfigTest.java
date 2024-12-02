package com.example.kitchensink.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SecurityConfigTest {

    @InjectMocks
    private SecurityConfig securityConfig;

    @Mock
    private CustomUserDetailsService mockUserDetailsService;

    @Mock
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Mock
    private HttpSecurity httpSecurity;

    @Mock
    private CsrfConfigurer<HttpSecurity> csrfConfigurer; // Correctly mock CsrfConfigurer

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPasswordEncoder() {
        PasswordEncoder encoder = SecurityConfig.passwordEncoder();

        assertNotNull(encoder, "PasswordEncoder should not be null");

        String rawPassword = "password123";
        String encodedPassword = encoder.encode(rawPassword);
        assertTrue(encoder.matches(rawPassword, encodedPassword), "Password should match the encoded version");
    }
}
