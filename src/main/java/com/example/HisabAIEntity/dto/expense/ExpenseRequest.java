package com.example.HisabAIEntity.dto.expense;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpenseRequest(
        @NotNull(message = "Branch is required") Long branchId,
        @NotNull(message = "Category is required") Long categoryId,
        @NotNull(message = "Amount is required") @DecimalMin(value = "0.01", message = "Amount must be positive") BigDecimal amount,
        @NotNull(message = "Expense date is required") LocalDate expenseDate,
        String note,
        String attachmentPath
) {}
