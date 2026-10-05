package com.example.HisabAIEntity.entity.product;

import com.example.HisabAIEntity.entity.common.SoftDeletableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Gap fix: Brand was referenced in the Step 3 DB spec (products.brand_id)
 * but had no corresponding entity — Unit was intentionally simplified to
 * a plain string on Product, but Brand needs its own master-data table
 * since brands are managed independently (add/rename/deactivate) and are
 * shared across many products, unlike a free-text unit label.
 */
@Getter
@Setter
@Entity
@Table(name = "brands")
public class Brand extends SoftDeletableEntity {

    @Column(nullable = false)
    private String name;

    @Column(name = "logo_url")
    private String logoUrl;

    @Column(name = "is_active", nullable = false)
    private boolean active = true;
}