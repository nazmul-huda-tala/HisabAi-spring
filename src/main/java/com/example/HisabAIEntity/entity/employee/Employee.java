package com.example.HisabAIEntity.entity.employee;

import com.example.HisabAIEntity.entity.common.SoftDeletableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/** Extends a User with HR-specific fields; user_id links back to identity.users. */
@Getter
@Setter
@Entity
@Table(name = "employees")
public class Employee extends SoftDeletableEntity {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "branch_id", nullable = false)
    private Long branchId;

    @Column(name = "designation")
    private String designation;

    @Column(name = "salary", precision = 14, scale = 2)
    private BigDecimal salary;

    @Column(name = "joined_at")
    private LocalDate joinedAt;
}

