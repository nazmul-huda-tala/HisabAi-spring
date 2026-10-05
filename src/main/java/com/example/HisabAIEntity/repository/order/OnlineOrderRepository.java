package com.example.HisabAIEntity.repository.order;

import com.example.HisabAIEntity.entity.order.OnlineOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OnlineOrderRepository extends JpaRepository<OnlineOrder, Long> {

    List<OnlineOrder> findAllByBusinessId(Long businessId);
    Optional<OnlineOrder> findByIdAndBusinessId(Long id, Long businessId);
}
