package com.example.HisabAIEntity.entity.customer;

import com.example.HisabAIEntity.entity.common.SoftDeletableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer extends SoftDeletableEntity {

    @Column(nullable = false)
    private String name;

    private String phone;

    private String email;

    private String address;

    // Gap fix: credit_limit existed in the UI form but not in the DB.
    @Column(name = "credit_limit", precision = 14, scale = 2)
    private BigDecimal creditLimit = BigDecimal.ZERO;
}
