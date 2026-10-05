package com.example.HisabAIEntity.repository.approval;

import com.example.HisabAIEntity.entity.approval.Approval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApprovalRepository extends JpaRepository<Approval, Long> {

    List<Approval> findAllByBusinessId(Long businessId);
    Optional<Approval> findByIdAndBusinessId(Long id, Long businessId);
}
