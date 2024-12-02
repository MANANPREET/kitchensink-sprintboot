package com.example.kitchensink.service;

import com.example.kitchensink.dto.RegistrationDto;
import com.example.kitchensink.model.UserEntity;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);
    UserEntity findByEmail(String email);
    UserEntity findByUsername(String username);
}