package com.example.HisabAIEntity.dto.product;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(
        @NotBlank(message = "Category name is required") String name,
        Long parentCategoryId
) {}
