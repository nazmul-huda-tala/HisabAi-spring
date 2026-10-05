package com.example.HisabAIEntity.dto.product;

import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String name,
        String sku,
        String barcode,
        Long categoryId,
        Long brandId,
        String unit,
        BigDecimal costPrice,
        BigDecimal sellingPrice,
        BigDecimal taxRate,
        String taxCategory,
        Integer reorderLevel,
        String imageUrl,
        boolean active,
        /** Derived from inventory_transactions — never a stored column. Business-wide, across all branches. */
        Integer currentStock,
        boolean lowStock
) {}
