package com.example.HisabAIEntity.security;

import com.example.HisabAIEntity.entity.identity.Role;
import com.example.HisabAIEntity.entity.identity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Locale;

/** Wraps a User entity so Spring Security can carry businessId + role names alongside the standard UserDetails contract. */
public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    public Long getUserId() {
        return user.getId();
    }

    public Long getBusinessId() {
        return user.getBusinessId();
    }

    public User getUser() {
        return user;
    }

    public List<String> getRoleNames() {
        return user.getRoles().stream()
                .map(Role::getName)
                .map(name -> name.toUpperCase(Locale.ROOT))
                .toList();
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
        return getRoleNames().stream()
                .map(name -> new SimpleGrantedAuthority("ROLE_" + name))
                .map(a -> (GrantedAuthority) a)
                .toList();
    }

    @Override
    public String getPassword() {
        return user.getPasswordHash();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isActive() && !user.isDeleted();
    }
}
