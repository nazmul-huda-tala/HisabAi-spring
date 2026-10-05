package com.example.HisabAIEntity.entity.expense;

import com.example.HisabAIEntity.entity.common.TenantEntity;
import com.example.HisabAIEntity.entity.enums.ApprovalStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "expenses")
public class Expense extends TenantEntity {

    @Column(name = "branch_id", nullable = false)
    private Long branchId;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(nullable = false, precision = 14, scale = 2)
    private BigDecimal amount;

    @Column(name = "expense_date", nullable = false)
    private LocalDate expenseDate;

    private String note;

    @Column(name = "attachment_path")
    private String attachmentPath;

    // Denormalized cache of the latest decision in the `approvals` table
    // (see Approval.java for the authority rule) — lets list screens
    // filter by status without a join. Only the approval service may
    // write these two fields, in the same transaction as the Approval row.
    @Enumerated(EnumType.STRING)
    @Column(name = "approval_status", nullable = false)
    private ApprovalStatus approvalStatus = ApprovalStatus.NOT_REQUIRED;

    @Column(name = "approved_by")
    private Long approvedBy;

    @Column(name = "created_by", nullable = false)
    private Long createdBy;
}