package com.example.HisabAIEntity.entity.pos;

import com.example.HisabAIEntity.entity.common.TenantEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

/** Cash-drawer / shift tracking for the POS screen. */
@Getter
@Setter
@Entity
@Table(name = "pos_sessions")
public class PosSession extends TenantEntity {

    @Column(name = "branch_id", nullable = false)
    private Long branchId;

    @Column(name = "cashier_id", nullable = false)
    private Long cashierId;

    @Column(name = "opening_cash", precision = 14, scale = 2, nullable = false)
    private BigDecimal openingCash;

    @Column(name = "closing_cash", precision = 14, scale = 2)
    private BigDecimal closingCash;

    @Column(name = "opened_at", nullable = false)
    private Instant openedAt;

    @Column(name = "closed_at")
    private Instant closedAt;

    @Column(name = "is_open", nullable = false)
    private boolean open = true;
}


