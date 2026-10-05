package com.example.HisabAIEntity.entity.order;

import com.example.HisabAIEntity.entity.common.TenantEntity;
import com.example.HisabAIEntity.entity.enums.OrderFulfillmentStatus;
import com.example.HisabAIEntity.entity.enums.PaymentMethodType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Gap fix: a POS Sale completes instantly and has no delivery step, but
 * a public-storefront order does — it needs a status that progresses
 * over hours/days, a delivery address, and support for guest checkout
 * (no Customer account). Kept as its own entity rather than folding
 * into Sale so the two very different business processes (instant
 * till-sale vs. multi-day fulfillment) don't tangle one workflow's
 * states into the other's.
 *
 * Once a merchant marks an order DELIVERED (or otherwise ready to book
 * for accounting), the fulfillment service creates a matching Sale +
 * SaleItems (so it flows through the same inventory/payment ledger as
 * every other sale) and stores that Sale's id in `saleId`. Until then,
 * `saleId` is null — the order exists but hasn't hit the books yet.
 */
@Getter
@Setter
@Entity
@Table(name = "online_orders")
public class OnlineOrder extends TenantEntity {

    @Column(name = "branch_id")
    private Long branchId; // assigned once a branch picks up fulfillment; null while unassigned

    // Nullable — a storefront customer may check out as a guest with no
    // Customer account. When customerId is null, the guest* fields below
    // are the only way to reach them.
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "guest_name")
    private String guestName;

    @Column(name = "guest_phone")
    private String guestPhone;

    @Column(name = "shipping_address", nullable = false, length = 500)
    private String shippingAddress;

    @Column(name = "order_no", nullable = false, unique = true)
    private String orderNo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderFulfillmentStatus status = OrderFulfillmentStatus.PLACED;

    @Column(name = "placed_at", nullable = false)
    private Instant placedAt;

    @Column(name = "confirmed_at")
    private Instant confirmedAt;

    @Column(name = "shipped_at")
    private Instant shippedAt;

    @Column(name = "delivered_at")
    private Instant deliveredAt;

    @Column(name = "cancel_reason")
    private String cancelReason;

    @Column(name = "sub_total", precision = 14, scale = 2, nullable = false)
    private BigDecimal subTotal = BigDecimal.ZERO;

    @Column(name = "discount_amount", precision = 14, scale = 2, nullable = false)
    private BigDecimal discountAmount = BigDecimal.ZERO;

    @Column(name = "delivery_fee", precision = 14, scale = 2, nullable = false)
    private BigDecimal deliveryFee = BigDecimal.ZERO;

    @Column(name = "tax_amount", precision = 14, scale = 2, nullable = false)
    private BigDecimal taxAmount = BigDecimal.ZERO;

    @Column(name = "total_amount", precision = 14, scale = 2, nullable = false)
    private BigDecimal totalAmount = BigDecimal.ZERO;

    // What the customer intended to pay with at checkout (COD, bKash, etc.)
    // — the actual settlement still goes through the Payment ledger once
    // money is confirmed received.
    @Enumerated(EnumType.STRING)
    @Column(name = "intended_payment_method")
    private PaymentMethodType intendedPaymentMethod;

    // Set once this order has been booked into the accounting ledger
    // as a real Sale (see class-level note above). Null until then.
    @Column(name = "sale_id")
    private Long saleId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OnlineOrderItem> items = new ArrayList<>();
}