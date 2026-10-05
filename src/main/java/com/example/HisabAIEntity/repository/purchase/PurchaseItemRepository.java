package com.example.HisabAIEntity.repository.purchase;

import com.example.HisabAIEntity.entity.purchase.PurchaseItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseItemRepository extends JpaRepository<PurchaseItem, Long> {

    List<PurchaseItem> findAllByBusinessId(Long businessId);
    Optional<PurchaseItem> findByIdAndBusinessId(Long id, Long businessId);
}
