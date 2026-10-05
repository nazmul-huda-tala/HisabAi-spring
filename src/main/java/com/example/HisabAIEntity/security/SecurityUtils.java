package com.example.HisabAIEntity.security;

import com.example.HisabAIEntity.exception.UnauthorizedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Every tenant-scoped service call needs "who is asking" (userId) and "which business"
 * (businessId) — this reads both off the JWT-derived principal that JwtAuthenticationFilter
 * put into the SecurityContext, so services never have to take them as raw parameters.
 */
public final class SecurityUtils {

    private SecurityUtils() {}

    public static CustomUserDetails currentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !(auth.getPrincipal() instanceof CustomUserDetails details)) {
            throw new UnauthorizedException("No authenticated user in this request.");
        }
        return details;
    }

    public static Long currentUserId() {
        return currentUser().getUserId();
    }

    public static Long currentBusinessId() {
        return currentUser().getBusinessId();
    }
}
