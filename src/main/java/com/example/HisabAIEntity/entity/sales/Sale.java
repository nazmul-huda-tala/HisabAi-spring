package com.example.HisabAIEntity.entity.sales;
import com.example.HisabAIEntity.entity.common.TenantEntity;
import com.example.HisabAIEntity.entity.enums.SaleStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "sales")
public class Sale extends TenantEntity {

    @Column(name = "branch_id", nullable = false)
    private Long branchId;

    @Column(name = "customer_id")
    private Long customerId; // nullable -- walk-in customer

    @Column(name = "invoice_no", nullable = false, unique = true)
    private String invoiceNo;

    @Column(name = "sale_time", nullable = false)
    private Instant saleTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SaleStatus status = SaleStatus.DRAFT;

    @Column(name = "is_pos_sale", nullable = false)
    private boolean posSale = false;

    @Column(name = "sub_total", precision = 14, scale = 2, nullable = false)
    private BigDecimal subTotal = BigDecimal.ZERO;

    @Column(name = "discount_amount", precision = 14, scale = 2, nullable = false)
    private BigDecimal discountAmount = BigDecimal.ZERO;

    @Column(name = "tax_amount", precision = 14, scale = 2, nullable = false)
    private BigDecimal taxAmount = BigDecimal.ZERO;

    @Column(name = "total_amount", precision = 14, scale = 2, nullable = false)
    private BigDecimal totalAmount = BigDecimal.ZERO;

    @Column(name = "paid_amount", precision = 14, scale = 2, nullable = false)
    private BigDecimal paidAmount = BigDecimal.ZERO;

    @Column(name = "due_amount", precision = 14, scale = 2, nullable = false)
    private BigDecimal dueAmount = BigDecimal.ZERO;

    @Column(name = "created_by", nullable = false)
    private Long createdBy;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleItem> items = new ArrayList<>();
}


