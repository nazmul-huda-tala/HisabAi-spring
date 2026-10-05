package com.example.HisabAIEntity.security;

import com.example.HisabAIEntity.entity.identity.User;
import com.example.HisabAIEntity.repository.identity.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmailAndDeletedFalse(email)
                .orElseThrow(() -> new UsernameNotFoundException("No account found for " + email));
        return new CustomUserDetails(user);
    }

    /** Used by JwtAuthenticationFilter, which only has the userId (subject claim) from the token, not the email. */
    public UserDetails loadUserById(Long userId) {
        User user = userRepository.findByIdWithRoles(userId)
                .orElseThrow(() -> new UsernameNotFoundException("No account found for id " + userId));
        return new CustomUserDetails(user);
    }
}
