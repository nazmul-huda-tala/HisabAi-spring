package com.example.HisabAIEntity.repository.sales;

import com.example.HisabAIEntity.entity.sales.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    List<Sale> findAllByBusinessId(Long businessId);
    Optional<Sale> findByIdAndBusinessId(Long id, Long businessId);
}
