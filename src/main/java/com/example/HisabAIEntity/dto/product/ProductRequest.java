package com.example.HisabAIEntity.dto.product;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank(message = "Product name is required") String name,
        String sku,
        String barcode,
        Long categoryId,
        Long brandId,
        String unit,
        @DecimalMin(value = "0", message = "Cost price can't be negative") BigDecimal costPrice,
        @NotNull(message = "Selling price is required") @DecimalMin(value = "0", message = "Selling price can't be negative") BigDecimal sellingPrice,
        BigDecimal taxRate,
        String taxCategory,
        Integer reorderLevel,
        String imageUrl,
        Boolean active
) {}
