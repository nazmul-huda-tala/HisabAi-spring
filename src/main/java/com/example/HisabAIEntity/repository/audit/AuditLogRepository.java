package com.example.HisabAIEntity.repository.audit;

import com.example.HisabAIEntity.entity.audit.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

    List<AuditLog> findAllByBusinessId(Long businessId);
    Optional<AuditLog> findByIdAndBusinessId(Long id, Long businessId);
}
