package com.example.HisabAIEntity.repository.purchase;

import com.example.HisabAIEntity.entity.purchase.PurchaseReturn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseReturnRepository extends JpaRepository<PurchaseReturn, Long> {

    List<PurchaseReturn> findAllByBusinessId(Long businessId);
    Optional<PurchaseReturn> findByIdAndBusinessId(Long id, Long businessId);
}
