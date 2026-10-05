package com.example.HisabAIEntity.entity.common;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

/**
 * For MASTER DATA only (products, customers, suppliers, employees...).
 * Transactions (sales, purchases, payments, inventory_transactions) are
 * NEVER soft-deleted -- they are reversed/cancelled instead, per the
 * architecture doc.
 */
@Getter
@Setter
@MappedSuperclass
public abstract class SoftDeletableEntity extends TenantEntity {

    @Column(name = "is_deleted", nullable = false)
    private boolean deleted = false;
}


