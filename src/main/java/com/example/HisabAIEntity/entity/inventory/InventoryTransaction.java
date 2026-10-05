package com.example.HisabAIEntity.entity.inventory;

import com.example.HisabAIEntity.entity.common.TenantEntity;
import com.example.HisabAIEntity.entity.enums.InventoryTransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Append-only ledger. Current stock is derived via SUM(quantity) per
 * product+branch -- there is NO mutable current_stock column anywhere.
 * (Flagged for a future cached/materialized balance table if this
 * doesn't scale.)
 */
@Getter
@Setter
@Entity
@Table(name = "inventory_transactions")
public class InventoryTransaction extends TenantEntity {

    @Column(name = "branch_id", nullable = false)
    private Long branchId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    private InventoryTransactionType transactionType;

    /** Positive for IN movements, negative for OUT movements. */
    @Column(nullable = false)
    private Integer quantity;

    /** Points at the sale_item / purchase_item / adjustment / transfer row that caused this entry. */
    @Column(name = "reference_type")
    private String referenceType;

    @Column(name = "reference_id")
    private Long referenceId;

    @Column(name = "created_by")
    private Long createdBy;
}


