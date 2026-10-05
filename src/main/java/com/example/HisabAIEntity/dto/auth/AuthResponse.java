package com.example.HisabAIEntity.dto.auth;

import java.util.List;

public record AuthResponse(
        String accessToken,
        String refreshToken,
        Long userId,
        Long businessId,
        String name,
        String email,
        List<String> roles
) {}
