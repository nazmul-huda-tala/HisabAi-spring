package com.example.HisabAIEntity.dto.product;

import jakarta.validation.constraints.NotBlank;

public record BrandRequest(
        @NotBlank(message = "Brand name is required") String name,
        String logoUrl,
        Boolean active
) {}
