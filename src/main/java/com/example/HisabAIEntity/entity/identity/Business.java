package com.example.HisabAIEntity.entity.identity;


import com.example.HisabAIEntity.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/** Root tenant entity. Every other table is scoped to a business_id. */
@Getter
@Setter
@Entity
@Table(name = "businesses")
public class Business extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(name = "trade_license_no")
    private String tradeLicenseNo;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "logo_url")
    private String logoUrl;

    @Column(name = "is_active", nullable = false)
    private boolean active = true;
}
