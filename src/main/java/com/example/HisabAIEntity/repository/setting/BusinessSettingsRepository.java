package com.example.HisabAIEntity.repository.setting;

import com.example.HisabAIEntity.entity.setting.BusinessSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusinessSettingsRepository extends JpaRepository<BusinessSettings, Long> {

    List<BusinessSettings> findAllByBusinessId(Long businessId);
    Optional<BusinessSettings> findByIdAndBusinessId(Long id, Long businessId);
}
