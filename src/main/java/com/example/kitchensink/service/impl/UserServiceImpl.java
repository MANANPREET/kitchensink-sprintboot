package com.example.kitchensink.service.impl;

import com.example.kitchensink.dto.RegistrationDto;
import com.example.kitchensink.model.Role;
import com.example.kitchensink.model.UserEntity;
import com.example.kitchensink.repository.RoleRepository;
import com.example.kitchensink.repository.UserRepository;
import com.example.kitchensink.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Implementation of the UserService interface, handling user-related operations.
 */
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    /**
     * Constructor to initialize dependencies for the UserServiceImpl class.
     *
     * @param userRepository The repository for UserEntity.
     * @param roleRepository The repository for Role.
     * @param passwordEncoder The password encoder for hashing passwords.
     */
    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(RegistrationDto registrationDto) {
        UserEntity user = new UserEntity();
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));

        // Assigning the role "ADMIN" to the user
        Role role = roleRepository.findByName("USER");
        user.setRoles(Arrays.asList(role));

        userRepository.save(user);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
