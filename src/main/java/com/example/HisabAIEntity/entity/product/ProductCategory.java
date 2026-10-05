package com.example.HisabAIEntity.entity.product;

import com.example.HisabAIEntity.entity.common.SoftDeletableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product_categories")
public class ProductCategory extends SoftDeletableEntity {

    @Column(nullable = false)
    private String name;

    @Column(name = "parent_category_id")
    private Long parentCategoryId;
}

