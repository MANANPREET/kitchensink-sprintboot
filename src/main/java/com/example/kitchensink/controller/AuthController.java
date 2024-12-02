package com.example.kitchensink.controller;

import com.example.kitchensink.dto.RegistrationDto;
import com.example.kitchensink.model.UserEntity;
import com.example.kitchensink.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Authorization Controller
 */
@Controller
public class AuthController {
    private UserService userService;

    // Constructor to inject the UserService
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint to show login page
     * @return login page
     */
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    /**
     * Endpoint to show registration form
     * @param model
     * @return register page
     */
    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "register";
    }

    /**
     * Endpoint to handle form submission for registration
     * @param user
     * @param result
     * @param model
     * @return redirect to list of members page
     */
    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user")RegistrationDto user,
                           BindingResult result, Model model) {
        UserEntity existingUserEmail = userService.findByEmail(user.getEmail());
        if(existingUserEmail != null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty()) {
            return "redirect:/register?fail";
        }
        UserEntity existingUserUsername = userService.findByUsername(user.getUsername());
        if(existingUserUsername != null && existingUserUsername.getUsername() != null && !existingUserUsername.getUsername().isEmpty()) {
            return "redirect:/register?fail";
        }
        if(result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/member?success";
    }
}
