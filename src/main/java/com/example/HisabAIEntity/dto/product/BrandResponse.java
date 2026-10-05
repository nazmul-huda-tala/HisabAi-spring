package com.example.HisabAIEntity.dto.product;

public record BrandResponse(
        Long id,
        String name,
        String logoUrl,
        boolean active
) {}
