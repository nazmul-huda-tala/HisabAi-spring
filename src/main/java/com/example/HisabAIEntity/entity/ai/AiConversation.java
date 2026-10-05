package com.example.HisabAIEntity.entity.ai;

import com.example.HisabAIEntity.entity.common.TenantEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ai_conversations")
public class AiConversation extends TenantEntity {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    private String title;
}
