package com.example.HisabAIEntity.repository.ai;

import com.example.HisabAIEntity.entity.ai.AiConversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AiConversationRepository extends JpaRepository<AiConversation, Long> {

    List<AiConversation> findAllByBusinessId(Long businessId);
    Optional<AiConversation> findByIdAndBusinessId(Long id, Long businessId);
}
