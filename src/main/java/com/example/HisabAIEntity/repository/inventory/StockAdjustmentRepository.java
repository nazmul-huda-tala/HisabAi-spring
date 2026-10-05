package com.example.HisabAIEntity.repository.inventory;

import com.example.HisabAIEntity.entity.inventory.StockAdjustment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockAdjustmentRepository extends JpaRepository<StockAdjustment, Long> {

    List<StockAdjustment> findAllByBusinessId(Long businessId);
    Optional<StockAdjustment> findByIdAndBusinessId(Long id, Long businessId);
}
