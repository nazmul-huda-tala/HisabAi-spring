package com.example.HisabAIEntity.entity.product;

import com.example.HisabAIEntity.entity.common.SoftDeletableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product extends SoftDeletableEntity {

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String sku;

    private String barcode;

    @Column(name = "category_id")
    private Long categoryId;

    // Gap fix: Brand was missing entirely — products.brand_id was in the
    // Step 3 DB spec but had no entity or column here. Nullable because
    // not every product has a brand (e.g. loose produce, generic items).
    @Column(name = "brand_id")
    private Long brandId;

    private String unit; // pcs, kg, litre...

    @Column(name = "cost_price", precision = 14, scale = 2)
    private BigDecimal costPrice;

    @Column(name = "selling_price", precision = 14, scale = 2, nullable = false)
    private BigDecimal sellingPrice;

    // Gap fix: tax rate/category was missing at product level (only
    // transaction-level tax_amount existed).
    @Column(name = "tax_rate", precision = 5, scale = 2)
    private BigDecimal taxRate;

    @Column(name = "tax_category")
    private String taxCategory;

    @Column(name = "reorder_level")
    private Integer reorderLevel;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "is_active", nullable = false)
    private boolean active = true;
}