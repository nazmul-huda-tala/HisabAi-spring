package com.example.HisabAIEntity.repository.payment;

import com.example.HisabAIEntity.entity.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findAllByBusinessId(Long businessId);
    Optional<Payment> findByIdAndBusinessId(Long id, Long businessId);
}
