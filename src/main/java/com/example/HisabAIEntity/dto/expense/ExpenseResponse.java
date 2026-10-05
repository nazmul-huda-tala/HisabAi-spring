package com.example.HisabAIEntity.dto.expense;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpenseResponse(
        Long id,
        Long branchId,
        Long categoryId,
        BigDecimal amount,
        LocalDate expenseDate,
        String note,
        String attachmentPath,
        String approvalStatus,
        Long approvedBy,
        Long createdBy
) {}
