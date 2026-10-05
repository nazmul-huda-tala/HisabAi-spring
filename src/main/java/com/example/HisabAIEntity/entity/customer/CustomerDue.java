package com.example.HisabAIEntity.entity.customer;

import com.example.HisabAIEntity.entity.common.TenantEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/** One row per sale that has an outstanding balance; closed as payments come in. */
@Getter
@Setter
@Entity
@Table(name = "customer_dues")
public class CustomerDue extends TenantEntity {

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "sale_id", nullable = false)
    private Long saleId;

    @Column(name = "original_amount", precision = 14, scale = 2, nullable = false)
    private BigDecimal originalAmount;

    @Column(name = "due_amount", precision = 14, scale = 2, nullable = false)
    private BigDecimal dueAmount;

    @Column(name = "is_settled", nullable = false)
    private boolean settled = false;
}

