package com.example.HisabAIEntity.repository.product;

import com.example.HisabAIEntity.entity.product.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

    List<ProductCategory> findAllByBusinessId(Long businessId);
    Optional<ProductCategory> findByIdAndBusinessId(Long id, Long businessId);
    List<ProductCategory> findAllByBusinessIdAndDeletedFalse(Long businessId);
}
