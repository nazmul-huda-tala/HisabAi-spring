package com.example.HisabAIEntity.entity.inventory;

import com.example.HisabAIEntity.entity.common.TenantEntity;
import com.example.HisabAIEntity.entity.enums.AdjustmentReason;
import com.example.HisabAIEntity.entity.enums.ApprovalStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/** Creates one InventoryTransaction (ADJUSTMENT_IN/OUT) once approved. */
@Getter
@Setter
@Entity
@Table(name = "stock_adjustments")
public class StockAdjustment extends TenantEntity {

    @Column(name = "branch_id", nullable = false)
    private Long branchId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AdjustmentReason reason;

    private String note;

    // Denormalized cache of the latest decision in the `approvals` table
    // (see Approval.java for the authority rule) — lets list screens
    // filter by status without a join. Only the approval service may
    // write these two fields, in the same transaction as the Approval row.
    @Enumerated(EnumType.STRING)
    @Column(name = "approval_status", nullable = false)
    private ApprovalStatus approvalStatus = ApprovalStatus.PENDING;

    @Column(name = "approved_by")
    private Long approvedBy;

    @Column(name = "requested_by", nullable = false)
    private Long requestedBy;
}