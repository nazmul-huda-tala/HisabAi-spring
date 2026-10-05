package com.example.HisabAIEntity.repository.supplier;

import com.example.HisabAIEntity.entity.supplier.SupplierPayable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierPayableRepository extends JpaRepository<SupplierPayable, Long> {

    List<SupplierPayable> findAllByBusinessId(Long businessId);
    Optional<SupplierPayable> findByIdAndBusinessId(Long id, Long businessId);
    List<SupplierPayable> findAllBySupplierIdAndBusinessIdOrderByCreatedAtDesc(Long supplierId, Long businessId);

    @Query("select coalesce(sum(p.payableAmount), 0) from SupplierPayable p " +
            "where p.supplierId = :supplierId and p.businessId = :businessId and p.settled = false")
    BigDecimal sumOutstandingBySupplier(@Param("supplierId") Long supplierId, @Param("businessId") Long businessId);
}
