package com.example.HisabAIEntity.entity.audit;

import com.example.HisabAIEntity.entity.common.TenantEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "audit_logs")
public class AuditLog extends TenantEntity {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String action; // e.g. "PRODUCT_UPDATED", "SALE_CANCELLED"

    @Column(name = "entity_type", nullable = false)
    private String entityType;

    @Column(name = "entity_id", nullable = false)
    private Long entityId;

    @Lob
    @Column(name = "old_value")
    private String oldValue; // JSON snapshot

    @Lob
    @Column(name = "new_value")
    private String newValue; // JSON snapshot

    @Column(name = "ip_address")
    private String ipAddress;
}

