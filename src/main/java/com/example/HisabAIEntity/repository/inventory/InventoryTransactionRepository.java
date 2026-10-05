package com.example.HisabAIEntity.repository.inventory;

import com.example.HisabAIEntity.entity.inventory.InventoryTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryTransactionRepository extends JpaRepository<InventoryTransaction, Long> {

    List<InventoryTransaction> findAllByBusinessId(Long businessId);
    Optional<InventoryTransaction> findByIdAndBusinessId(Long id, Long businessId);
    List<InventoryTransaction> findAllByProductIdAndBusinessIdOrderByCreatedAtDesc(Long productId, Long businessId);

    /** Current stock is derived, never stored — this is the single source of truth for "how many are left". */
    @Query("select coalesce(sum(t.quantity), 0) from InventoryTransaction t " +
            "where t.productId = :productId and t.businessId = :businessId")
    Integer sumQuantityByProduct(@Param("productId") Long productId, @Param("businessId") Long businessId);

    @Query("select coalesce(sum(t.quantity), 0) from InventoryTransaction t " +
            "where t.productId = :productId and t.branchId = :branchId and t.businessId = :businessId")
    Integer sumQuantityByProductAndBranch(@Param("productId") Long productId, @Param("branchId") Long branchId, @Param("businessId") Long businessId);
}
