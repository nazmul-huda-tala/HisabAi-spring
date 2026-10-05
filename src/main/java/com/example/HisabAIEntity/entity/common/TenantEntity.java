package com.example.HisabAIEntity.entity.common;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

/**
 * Adds business_id (tenant) scoping. Every table except `businesses`
 * itself extends this, per the multi-tenant architecture.
 */
@Getter
@Setter
@MappedSuperclass
public abstract class TenantEntity extends BaseEntity {

    @Column(name = "business_id", nullable = false)
    private Long businessId;
}
