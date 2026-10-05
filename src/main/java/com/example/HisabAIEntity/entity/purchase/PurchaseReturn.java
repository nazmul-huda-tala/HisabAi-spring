package com.example.HisabAIEntity.entity.purchase;

import com.example.HisabAIEntity.entity.common.TenantEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** Gap fix: dedicated purchase-return header, previously only a generic inventory_transactions row. */
@Getter
@Setter
@Entity
@Table(name = "purchase_returns")
public class PurchaseReturn extends TenantEntity {

    @Column(name = "purchase_id", nullable = false)
    private Long purchaseId;

    @Column(name = "branch_id", nullable = false)
    private Long branchId;

    @Column(name = "return_date", nullable = false)
    private LocalDate returnDate;

    private String reason;

    @Column(name = "total_amount", precision = 14, scale = 2, nullable = false)
    private BigDecimal totalAmount = BigDecimal.ZERO;

    @Column(name = "created_by", nullable = false)
    private Long createdBy;

    @OneToMany(mappedBy = "purchaseReturn", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PurchaseReturnItem> items = new ArrayList<>();
}

