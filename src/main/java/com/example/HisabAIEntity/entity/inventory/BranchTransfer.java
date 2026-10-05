package com.example.HisabAIEntity.entity.inventory;

import com.example.HisabAIEntity.entity.common.TenantEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Header table linking a paired TRANSFER_OUT (from_branch) and
 * TRANSFER_IN (to_branch) InventoryTransaction pair. Addresses the
 * earlier-identified gap of no branch-transfer header table.
 */
@Getter
@Setter
@Entity
@Table(name = "branch_transfers")
public class BranchTransfer extends TenantEntity {

    @Column(name = "from_branch_id", nullable = false)
    private Long fromBranchId;

    @Column(name = "to_branch_id", nullable = false)
    private Long toBranchId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "out_transaction_id")
    private Long outTransactionId;

    @Column(name = "in_transaction_id")
    private Long inTransactionId;

    @Column(name = "initiated_by", nullable = false)
    private Long initiatedBy;

    private String note;
}
