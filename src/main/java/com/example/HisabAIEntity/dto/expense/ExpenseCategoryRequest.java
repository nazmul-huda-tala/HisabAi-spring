package com.example.HisabAIEntity.dto.expense;

import jakarta.validation.constraints.NotBlank;

public record ExpenseCategoryRequest(
        @NotBlank(message = "Category name is required") String name
) {}
