package com.example.HisabAIEntity.repository.customer;

import com.example.HisabAIEntity.entity.customer.CustomerDue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerDueRepository extends JpaRepository<CustomerDue, Long> {

    List<CustomerDue> findAllByBusinessId(Long businessId);
    Optional<CustomerDue> findByIdAndBusinessId(Long id, Long businessId);
    List<CustomerDue> findAllByCustomerIdAndBusinessIdOrderByCreatedAtDesc(Long customerId, Long businessId);
    List<CustomerDue> findAllByCustomerIdAndBusinessIdAndSettledFalse(Long customerId, Long businessId);

    @Query("select coalesce(sum(d.dueAmount), 0) from CustomerDue d " +
            "where d.customerId = :customerId and d.businessId = :businessId and d.settled = false")
    BigDecimal sumOutstandingByCustomer(@Param("customerId") Long customerId, @Param("businessId") Long businessId);
}
