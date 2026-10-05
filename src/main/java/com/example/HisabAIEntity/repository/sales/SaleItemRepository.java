package com.example.HisabAIEntity.repository.sales;

import com.example.HisabAIEntity.entity.sales.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {

    List<SaleItem> findAllByBusinessId(Long businessId);
    Optional<SaleItem> findByIdAndBusinessId(Long id, Long businessId);
}
