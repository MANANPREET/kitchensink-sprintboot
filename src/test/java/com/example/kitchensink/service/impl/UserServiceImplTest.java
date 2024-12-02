package com.example.kitchensink.service.impl;

import com.example.kitchensink.dto.RegistrationDto;
import com.example.kitchensink.model.Role;
import com.example.kitchensink.model.UserEntity;
import com.example.kitchensink.repository.RoleRepository;
import com.example.kitchensink.repository.UserRepository;
import com.example.kitchensink.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private RegistrationDto registrationDto;

    @BeforeEach
    void setUp() {
        // Initialize the mocks
        MockitoAnnotations.openMocks(this);

        // Initialize the RegistrationDto with setters
        registrationDto = new RegistrationDto();
        registrationDto.setUsername("testuser");
        registrationDto.setEmail("testuser@example.com");
        registrationDto.setPassword("password123");
    }

    @Test
    void saveUser_ShouldSaveUser() {
        // Mock the Role object
        Role role = Mockito.mock(Role.class);
        Mockito.when(role.getName()).thenReturn("ADMIN");

        // Arrange: Mock the password encoding and role retrieval
        Mockito.when(passwordEncoder.encode(registrationDto.getPassword())).thenReturn("encodedPassword");
        Mockito.when(roleRepository.findByName("ADMIN")).thenReturn(role);

        // Act: Call the saveUser method
        userService.saveUser(registrationDto);

        // Assert: Verify that the save method is called on userRepository
        verify(userRepository).save(any(UserEntity.class));
    }
}
