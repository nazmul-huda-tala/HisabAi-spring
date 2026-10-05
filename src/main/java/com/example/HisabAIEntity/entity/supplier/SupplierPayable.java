package com.example.HisabAIEntity.entity.supplier;

import com.example.HisabAIEntity.entity.common.TenantEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "supplier_payables")
public class SupplierPayable extends TenantEntity {

    @Column(name = "supplier_id", nullable = false)
    private Long supplierId;

    @Column(name = "purchase_id", nullable = false)
    private Long purchaseId;

    @Column(name = "original_amount", precision = 14, scale = 2, nullable = false)
    private BigDecimal originalAmount;

    @Column(name = "payable_amount", precision = 14, scale = 2, nullable = false)
    private BigDecimal payableAmount;

    @Column(name = "is_settled", nullable = false)
    private boolean settled = false;
}

