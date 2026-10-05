package com.example.HisabAIEntity.dto.supplier;

import java.math.BigDecimal;
import java.time.Instant;

public record SupplierPayableResponse(
        Long id,
        Long supplierId,
        Long purchaseId,
        BigDecimal originalAmount,
        BigDecimal payableAmount,
        boolean settled,
        Instant createdAt
) {}
