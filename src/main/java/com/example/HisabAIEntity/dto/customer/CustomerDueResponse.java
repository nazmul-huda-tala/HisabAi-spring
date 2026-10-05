package com.example.HisabAIEntity.dto.customer;

import java.math.BigDecimal;
import java.time.Instant;

public record CustomerDueResponse(
        Long id,
        Long customerId,
        Long saleId,
        BigDecimal originalAmount,
        BigDecimal dueAmount,
        boolean settled,
        Instant createdAt
) {}
