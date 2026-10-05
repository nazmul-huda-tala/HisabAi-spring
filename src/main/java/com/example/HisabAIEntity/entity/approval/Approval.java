package com.example.HisabAIEntity.entity.approval;

import com.example.HisabAIEntity.entity.common.TenantEntity;
import com.example.HisabAIEntity.entity.enums.ApprovableEntityType;
import com.example.HisabAIEntity.entity.enums.ApprovalStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;


@Getter
@Setter
@Entity
@Table(name = "approvals")
public class Approval extends TenantEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "entity_type", nullable = false)
    private ApprovableEntityType entityType;

    @Column(name = "entity_id", nullable = false)
    private Long entityId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApprovalStatus status = ApprovalStatus.PENDING;

    @Column(name = "requested_by", nullable = false)
    private Long requestedBy;

    @Column(name = "approved_by")
    private Long approvedBy;

    @Column(name = "decided_at")
    private Instant decidedAt;

    @Column(name = "remarks")
    private String remarks;
}