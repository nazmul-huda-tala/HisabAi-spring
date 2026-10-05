package com.example.HisabAIEntity.repository.supplier;

import com.example.HisabAIEntity.entity.supplier.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    List<Supplier> findAllByBusinessId(Long businessId);
    Optional<Supplier> findByIdAndBusinessId(Long id, Long businessId);
    List<Supplier> findAllByBusinessIdAndDeletedFalse(Long businessId);
}
