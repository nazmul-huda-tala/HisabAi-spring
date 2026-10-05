package com.example.HisabAIEntity.repository.product;

import com.example.HisabAIEntity.entity.product.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {

    List<ProductVariant> findAllByBusinessId(Long businessId);
    Optional<ProductVariant> findByIdAndBusinessId(Long id, Long businessId);
    List<ProductVariant> findAllByBusinessIdAndDeletedFalse(Long businessId);
}
