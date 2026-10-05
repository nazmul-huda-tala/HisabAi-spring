package com.example.HisabAIEntity.dto.customer;

import java.math.BigDecimal;

public record CustomerResponse(
        Long id,
        String name,
        String phone,
        String email,
        String address,
        BigDecimal creditLimit,
        /** Sum of unsettled customer_dues rows — derived, not stored. */
        BigDecimal outstandingDue
) {}
