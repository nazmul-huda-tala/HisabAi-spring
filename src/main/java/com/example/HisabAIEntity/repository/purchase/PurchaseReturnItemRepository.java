package com.example.HisabAIEntity.repository.purchase;

import com.example.HisabAIEntity.entity.purchase.PurchaseReturnItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseReturnItemRepository extends JpaRepository<PurchaseReturnItem, Long> {

    List<PurchaseReturnItem> findAllByBusinessId(Long businessId);
    Optional<PurchaseReturnItem> findByIdAndBusinessId(Long id, Long businessId);
}
