package com.example.HisabAIEntity.repository.inventory;

import com.example.HisabAIEntity.entity.inventory.BranchTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BranchTransferRepository extends JpaRepository<BranchTransfer, Long> {

    List<BranchTransfer> findAllByBusinessId(Long businessId);
    Optional<BranchTransfer> findByIdAndBusinessId(Long id, Long businessId);
}
