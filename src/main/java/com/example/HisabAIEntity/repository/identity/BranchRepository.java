package com.example.HisabAIEntity.repository.identity;

import com.example.HisabAIEntity.entity.identity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

    List<Branch> findAllByBusinessId(Long businessId);
    Optional<Branch> findByIdAndBusinessId(Long id, Long businessId);
    List<Branch> findAllByBusinessIdAndDeletedFalse(Long businessId);
}
