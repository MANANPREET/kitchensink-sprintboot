package com.example.kitchensink.security;

import com.example.kitchensink.model.UserEntity;
import com.example.kitchensink.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * CustomUserDetailsService implements UserDetailsService to load user-specific data during authentication.
 * It fetches the user details from the UserRepository and converts them into a Spring Security User object.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Loads a user by username. If the user is found, it returns a User object with roles.
     * If the user is not found, it throws a UsernameNotFoundException.
     *
     * @param username The username of the user to retrieve
     * @return A UserDetails object containing user information and roles
     * @throws UsernameNotFoundException If the user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch user from the database using the username
        UserEntity user = userRepository.findFirstByUsername(username);

        // If the user is found, create a UserDetails object with the user's roles
        if (user != null) {
            User authUser = new User(
                    user.getUsername(),
                    user.getPassword(),
                    user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                            .collect(Collectors.toList())
            );
            return authUser;
        } else {
            // If user is not found, throw an exception
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }
}
