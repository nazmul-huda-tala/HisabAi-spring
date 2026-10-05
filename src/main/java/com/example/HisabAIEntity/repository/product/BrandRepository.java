package com.example.HisabAIEntity.repository.product;

import com.example.HisabAIEntity.entity.product.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    List<Brand> findAllByBusinessId(Long businessId);
    Optional<Brand> findByIdAndBusinessId(Long id, Long businessId);
    List<Brand> findAllByBusinessIdAndDeletedFalse(Long businessId);
}
