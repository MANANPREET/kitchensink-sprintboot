package com.example.kitchensink.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.authentication.AuthenticationManager;

/**
 * SecurityConfig is the main security configuration class.
 * It configures Spring Security settings such as HTTP security, form login, password encoding, and authentication manager.
 */
@Configuration
@EnableWebSecurity  // Enable Spring Security
@EnableMethodSecurity  // Enable method security (e.g., @PreAuthorize)
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    // Constructor injection for CustomUserDetailsService
    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * Password encoder bean to use BCrypt hashing algorithm for password encoding.
     *
     * @return BCryptPasswordEncoder instance
     */
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Security filter chain to configure HTTP security such as login, logout, and authorization rules.
     *
     * @param http HttpSecurity object for configuring security settings
     * @return Configured SecurityFilterChain
     * @throws Exception If there is a security configuration error
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()  // Disable CSRF for simplicity (in a real app, consider using CSRF protection)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login", "/register", "/clubs", "/error", "/css/**", "/js/**", "/assets/**", "/static/**", "/register/save").permitAll()  // Public endpoints
                        .anyRequest().authenticated()  // All other requests require authentication
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .defaultSuccessUrl("/member?login=true", true)  // Redirect after login
                        .loginProcessingUrl("/login")
                        .failureUrl("/login?error=true")  // URL for login failure
                        .permitAll()  // Allow anyone to access the login page
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")  // Redirect after logout
                        .permitAll()  // Allow anyone to log out
                );

        return http.build();  // Build and return the configured security chain
    }

    /**
     * AuthenticationManager bean used for authentication.
     *
     * @param http HttpSecurity object to access AuthenticationManagerBuilder
     * @return AuthenticationManager instance
     * @throws Exception If there is an authentication manager error
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }
}
