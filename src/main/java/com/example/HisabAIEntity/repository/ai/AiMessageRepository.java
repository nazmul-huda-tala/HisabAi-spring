package com.example.HisabAIEntity.repository.ai;

import com.example.HisabAIEntity.entity.ai.AiMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AiMessageRepository extends JpaRepository<AiMessage, Long> {

    List<AiMessage> findAllByBusinessId(Long businessId);
    Optional<AiMessage> findByIdAndBusinessId(Long id, Long businessId);
}
