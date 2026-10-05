package com.example.HisabAIEntity.repository.identity;

import com.example.HisabAIEntity.entity.identity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByBusinessId(Long businessId);
    Optional<User> findByIdAndBusinessId(Long id, Long businessId);
    List<User> findAllByBusinessIdAndDeletedFalse(Long businessId);

    /**
     * Login looks the user up by email alone (email is unique per business, and a user doesn't
     * know their businessId until after login). Roles are eagerly fetched here so
     * CustomUserDetails.getAuthorities() works after the transaction that loaded this closes.
     */
    @EntityGraph(attributePaths = "roles")
    @Query("select u from User u where u.email = :email and u.deleted = false")
    Optional<User> findByEmailAndDeletedFalse(@Param("email") String email);

    @EntityGraph(attributePaths = "roles")
    @Query("select u from User u where u.id = :id")
    Optional<User> findByIdWithRoles(@Param("id") Long id);

    boolean existsByEmailAndBusinessId(String email, Long businessId);
}
