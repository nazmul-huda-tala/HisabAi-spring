package com.example.HisabAIEntity.repository.identity;

import com.example.HisabAIEntity.entity.identity.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {
}
