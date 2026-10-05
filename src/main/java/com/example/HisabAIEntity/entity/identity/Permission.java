package com.example.HisabAIEntity.entity.identity;

import com.example.HisabAIEntity.entity.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/** Global lookup table (e.g. "product:create", "sales:approve"). Not tenant-scoped. */
@Getter
@Setter
@Entity
@Table(name = "permissions")
public class Permission extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String code;

    private String description;
}

