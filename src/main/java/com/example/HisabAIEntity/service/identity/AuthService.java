package com.example.HisabAIEntity.service.identity;

import com.example.HisabAIEntity.dto.auth.AuthResponse;
import com.example.HisabAIEntity.dto.auth.LoginRequest;
import com.example.HisabAIEntity.dto.auth.RegisterRequest;
import com.example.HisabAIEntity.entity.identity.Business;
import com.example.HisabAIEntity.entity.identity.RefreshToken;
import com.example.HisabAIEntity.entity.identity.Role;
import com.example.HisabAIEntity.entity.identity.User;
import com.example.HisabAIEntity.exception.DuplicateResourceException;
import com.example.HisabAIEntity.exception.UnauthorizedException;
import com.example.HisabAIEntity.repository.identity.BusinessRepository;
import com.example.HisabAIEntity.repository.identity.RefreshTokenRepository;
import com.example.HisabAIEntity.repository.identity.RoleRepository;
import com.example.HisabAIEntity.repository.identity.UserRepository;
import com.example.HisabAIEntity.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BusinessRepository businessRepository;
    private final RoleRepository roleRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            BusinessRepository businessRepository,
            RoleRepository roleRepository,
            RefreshTokenRepository refreshTokenRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.businessRepository = businessRepository;
        this.roleRepository = roleRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    /** Creates a brand-new tenant: the Business, a default OWNER role, and the first User (the owner). */
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByEmailAndDeletedFalse(request.email()).isPresent()) {
            throw new DuplicateResourceException("An account with this email already exists.");
        }

        Business business = new Business();
        business.setName(request.businessName());
        business.setEmail(request.email());
        business.setPhone(request.phone());
        business.setActive(true);
        business = businessRepository.save(business);

        Role ownerRole = new Role();
        ownerRole.setBusinessId(business.getId());
        ownerRole.setName("Owner");
        ownerRole.setSystemRole(true);
        ownerRole = roleRepository.save(ownerRole);

        User user = new User();
        user.setBusinessId(business.getId());
        user.setName(request.ownerName());
        user.setEmail(request.email());
        user.setPasswordHash(passwordEncoder.encode(request.password()));
        user.setPhone(request.phone());
        user.setActive(true);
        Set<Role> roles = new HashSet<>();
        roles.add(ownerRole);
        user.setRoles(roles);
        user = userRepository.save(user);

        return buildAuthResponse(user);
    }

    @Transactional
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        User user = userRepository.findByEmailAndDeletedFalse(request.email())
                .orElseThrow(() -> new UnauthorizedException("Invalid email or password."));

        return buildAuthResponse(user);
    }

    @Transactional
    public AuthResponse refresh(String refreshTokenValue) {
        if (!jwtService.isTokenValid(refreshTokenValue) || !"refresh".equals(jwtService.extractTokenType(refreshTokenValue))) {
            throw new UnauthorizedException("Refresh token is invalid or expired.");
        }

        String hash = hash(refreshTokenValue);
        RefreshToken stored = refreshTokenRepository.findByTokenHash(hash)
                .orElseThrow(() -> new UnauthorizedException("Refresh token is unrecognised."));

        if (stored.isRevoked() || stored.getExpiresAt().isBefore(Instant.now())) {
            throw new UnauthorizedException("Refresh token is no longer valid, please log in again.");
        }

        Long userId = jwtService.extractUserId(refreshTokenValue);
        User user = userRepository.findByIdWithRoles(userId)
                .orElseThrow(() -> new UnauthorizedException("Account no longer exists."));

        // Rotate: revoke the used refresh token and issue a new pair.
        stored.setRevoked(true);
        refreshTokenRepository.save(stored);

        return buildAuthResponse(user);
    }

    @Transactional
    public void logout(String refreshTokenValue) {
        refreshTokenRepository.findByTokenHash(hash(refreshTokenValue))
                .ifPresent(token -> {
                    token.setRevoked(true);
                    refreshTokenRepository.save(token);
                });
    }

    private AuthResponse buildAuthResponse(User user) {
        List<String> roleNames = user.getRoles().stream().map(Role::getName).toList();

        String accessToken = jwtService.generateAccessToken(user.getId(), user.getBusinessId(), user.getEmail(), roleNames);
        String refreshTokenValue = jwtService.generateRefreshToken(user.getId());

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setBusinessId(user.getBusinessId());
        refreshToken.setUserId(user.getId());
        refreshToken.setTokenHash(hash(refreshTokenValue));
        refreshToken.setExpiresAt(Instant.now().plusMillis(jwtService.getRefreshTokenExpirationMs()));
        refreshToken.setRevoked(false);
        refreshTokenRepository.save(refreshToken);

        return new AuthResponse(
                accessToken,
                refreshTokenValue,
                user.getId(),
                user.getBusinessId(),
                user.getName(),
                user.getEmail(),
                roleNames
        );
    }

    private String hash(String value) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = digest.digest(value.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new IllegalStateException("Unable to hash refresh token", e);
        }
    }
}
