package com.example.HisabAIEntity.repository.pos;

import com.example.HisabAIEntity.entity.pos.PosSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PosSessionRepository extends JpaRepository<PosSession, Long> {

    List<PosSession> findAllByBusinessId(Long businessId);
    Optional<PosSession> findByIdAndBusinessId(Long id, Long businessId);
}
