package com.example.HisabAIEntity.repository.sales;

import com.example.HisabAIEntity.entity.sales.SalesReturn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalesReturnRepository extends JpaRepository<SalesReturn, Long> {

    List<SalesReturn> findAllByBusinessId(Long businessId);
    Optional<SalesReturn> findByIdAndBusinessId(Long id, Long businessId);
}
