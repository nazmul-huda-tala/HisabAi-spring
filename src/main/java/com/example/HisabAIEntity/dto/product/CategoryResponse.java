package com.example.HisabAIEntity.dto.product;

public record CategoryResponse(
        Long id,
        String name,
        Long parentCategoryId
) {}
