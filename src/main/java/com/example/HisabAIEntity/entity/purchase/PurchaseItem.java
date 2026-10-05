package com.example.HisabAIEntity.entity.purchase;

import com.example.HisabAIEntity.entity.common.TenantEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/** unit_cost is a historical price snapshot -- never re-derived from products.cost_price. */
@Getter
@Setter
@Entity
@Table(name = "purchase_items")
public class PurchaseItem extends TenantEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_id", nullable = false)
    private Purchase purchase;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "unit_cost", precision = 14, scale = 2, nullable = false)
    private BigDecimal unitCost;

    @Column(name = "line_total", precision = 14, scale = 2, nullable = false)
    private BigDecimal lineTotal;
}

