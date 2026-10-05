package com.example.HisabAIEntity.dto.customer;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record CustomerRequest(
        @NotBlank(message = "Customer name is required") String name,
        String phone,
        String email,
        String address,
        @DecimalMin(value = "0", message = "Credit limit can't be negative") BigDecimal creditLimit
) {}
