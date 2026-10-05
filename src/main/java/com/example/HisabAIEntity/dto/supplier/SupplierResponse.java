package com.example.HisabAIEntity.dto.supplier;

import java.math.BigDecimal;

public record SupplierResponse(
        Long id,
        String name,
        String phone,
        String email,
        String address,
        String paymentTerms,
        /** Sum of unsettled supplier_payables rows — derived, not stored. */
        BigDecimal outstandingPayable
) {}
