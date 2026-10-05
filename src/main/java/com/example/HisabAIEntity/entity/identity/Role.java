package com.example.HisabAIEntity.entity.identity;

import com.example.HisabAIEntity.entity.common.TenantEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Owner, Admin, Manager, Cashier, Inventory Staff, Accountant,
 * Employee, Viewer -- seeded per business, editable at business level.
 */
@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role extends TenantEntity {

    @Column(nullable = false)
    private String name;

    @Column(name = "is_system_role", nullable = false)
    private boolean systemRole = false;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "role_permissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<Permission> permissions = new HashSet<>();
}


