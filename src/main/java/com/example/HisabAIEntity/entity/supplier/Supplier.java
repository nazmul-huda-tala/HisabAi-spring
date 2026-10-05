package com.example.HisabAIEntity.entity.supplier;

import com.example.HisabAIEntity.entity.common.SoftDeletableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "suppliers")
public class Supplier extends SoftDeletableEntity {

    @Column(nullable = false)
    private String name;

    private String phone;

    private String email;

    private String address;

    // Gap fix: payment_terms existed in the UI form but not in the DB.
    @Column(name = "payment_terms")
    private String paymentTerms; // e.g. "Net 30", "Cash on delivery"
}
