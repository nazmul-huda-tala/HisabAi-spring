package com.example.HisabAIEntity.repository.customer;

import com.example.HisabAIEntity.entity.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findAllByBusinessId(Long businessId);
    Optional<Customer> findByIdAndBusinessId(Long id, Long businessId);
    List<Customer> findAllByBusinessIdAndDeletedFalse(Long businessId);
}
