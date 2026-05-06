package com.sharief.secure_issue_tracker.auth.service;

import com.sharief.secure_issue_tracker.auth.dto.AuthResponse;
import com.sharief.secure_issue_tracker.auth.dto.LoginRequest;
import com.sharief.secure_issue_tracker.auth.dto.RegisterRequest;
import com.sharief.secure_issue_tracker.security.JwtService;
import com.sharief.secure_issue_tracker.user.entity.User;
import com.sharief.secure_issue_tracker.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String register(RegisterRequest request) {
        log.info("Register request received for email: {}", request.getEmail());

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            log.warn("Registration failed. Email already exists: {}", request.getEmail());
            throw new RuntimeException("Email already registered");
        }

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        userRepository.save(user);
        log.info("User registered successfully with email: {} and role: {}", user.getEmail(), user.getRole());

        return "User registered successfully";
    }

    public AuthResponse login(LoginRequest request) {
        log.info("Login attempt for email: {}", request.getEmail());

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> {
                    log.warn("Login failed. Email not found: {}", request.getEmail());
                    return new RuntimeException("Invalid email or password");
                });

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            log.warn("Login failed. Invalid password for email: {}", request.getEmail());
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtService.generateToken(user);
        log.info("Login successful for email: {} with role: {}", user.getEmail(), user.getRole());
        return new AuthResponse(token);
    }
}