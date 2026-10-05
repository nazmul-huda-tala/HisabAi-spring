package com.example.HisabAIEntity.repository.notification;

import com.example.HisabAIEntity.entity.notification.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findAllByBusinessId(Long businessId);
    Optional<Notification> findByIdAndBusinessId(Long id, Long businessId);
}
