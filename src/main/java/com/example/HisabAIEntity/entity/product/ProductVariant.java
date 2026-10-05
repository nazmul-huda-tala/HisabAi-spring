package com.example.HisabAIEntity.entity.product;

import com.example.HisabAIEntity.entity.common.SoftDeletableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "product_variants")
public class ProductVariant extends SoftDeletableEntity {

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(nullable = false)
    private String name; // e.g. "Size: L", "Color: Red"

    @Column(unique = true)
    private String sku;

    @Column(name = "extra_price", precision = 14, scale = 2)
    private BigDecimal extraPrice;
}
