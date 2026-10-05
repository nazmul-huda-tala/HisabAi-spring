package com.example.HisabAIEntity.entity.notification;


import com.example.HisabAIEntity.entity.common.TenantEntity;
import com.example.HisabAIEntity.entity.enums.NotificationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "notifications")
public class Notification extends TenantEntity {

    @Column(name = "user_id")
    private Long userId; // null = broadcast to all users of the business

    // Gap fix: dashboard needs to distinguish low-stock vs due alerts, etc.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType type;

    @Column(nullable = false)
    private String title;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "reference_type")
    private String referenceType;

    @Column(name = "reference_id")
    private Long referenceId;

    @Column(name = "is_read", nullable = false)
    private boolean read = false;
}


