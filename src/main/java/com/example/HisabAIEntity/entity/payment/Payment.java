package com.example.HisabAIEntity.entity.payment;

import com.example.HisabAIEntity.entity.common.TenantEntity;
import com.example.HisabAIEntity.entity.enums.PaymentDirection;
import com.example.HisabAIEntity.entity.enums.PaymentMethodType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Single ledger for all money movement (sale payment, due collection,
 * purchase payment, payable settlement, expense payment). Provider
 * (bKash/Nagad/Bank/Cash) is abstracted behind method + providerRef,
 * matching the BusinessAssistant-style Payment provider interface.
 */
@Getter
@Setter
@Entity
@Table(name = "payments")
public class Payment extends TenantEntity {

    @Column(name = "branch_id", nullable = false)
    private Long branchId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentDirection direction;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethodType method;

    @Column(name = "reference_type", nullable = false)
    private String referenceType; // "SALE", "PURCHASE", "CUSTOMER_DUE", "SUPPLIER_PAYABLE", "EXPENSE"

    @Column(name = "reference_id", nullable = false)
    private Long referenceId;

    @Column(nullable = false, precision = 14, scale = 2)
    private BigDecimal amount;

    @Column(name = "provider_transaction_id")
    private String providerTransactionId; // bKash/Nagad/bank txn id, if applicable

    @Column(name = "paid_at", nullable = false)
    private Instant paidAt;

    @Column(name = "received_by", nullable = false)
    private Long receivedBy;

    private String note;
}

