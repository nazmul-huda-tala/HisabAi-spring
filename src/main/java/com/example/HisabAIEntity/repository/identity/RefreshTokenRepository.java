package com.example.HisabAIEntity.repository.identity;

import com.example.HisabAIEntity.entity.identity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    List<RefreshToken> findAllByBusinessId(Long businessId);
    Optional<RefreshToken> findByIdAndBusinessId(Long id, Long businessId);
    Optional<RefreshToken> findByTokenHash(String tokenHash);
    List<RefreshToken> findAllByUserIdAndRevokedFalse(Long userId);
}
