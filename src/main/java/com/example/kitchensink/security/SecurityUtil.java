package com.example.kitchensink.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Utility class to fetch the current authenticated user's username.
 */
public class SecurityUtil {

    /**
     * Get the username of the currently authenticated user.
     * Returns null if the user is not authenticated or is anonymous.
     *
     * @return The username of the authenticated user or null if anonymous
     */
    public static String getSessionUser() {
        Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();  // Return the authenticated user's name
        }
        return null;  // Return null for anonymous users
    }
}
