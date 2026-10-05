package com.example.HisabAIEntity.repository.order;

import com.example.HisabAIEntity.entity.order.OnlineOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OnlineOrderItemRepository extends JpaRepository<OnlineOrderItem, Long> {

    List<OnlineOrderItem> findAllByBusinessId(Long businessId);
    Optional<OnlineOrderItem> findByIdAndBusinessId(Long id, Long businessId);
}
