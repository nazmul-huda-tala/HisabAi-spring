package com.example.HisabAIEntity.repository.identity;

import com.example.HisabAIEntity.entity.identity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findAllByBusinessId(Long businessId);
    Optional<Role> findByIdAndBusinessId(Long id, Long businessId);
    Optional<Role> findByNameAndBusinessId(String name, Long businessId);
}
