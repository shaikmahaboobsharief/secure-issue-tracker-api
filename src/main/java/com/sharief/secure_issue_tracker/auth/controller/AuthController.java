package com.sharief.secure_issue_tracker.auth.controller;


import com.sharief.secure_issue_tracker.auth.dto.AuthResponse;
import com.sharief.secure_issue_tracker.auth.dto.LoginRequest;
import com.sharief.secure_issue_tracker.auth.dto.RegisterRequest;
import com.sharief.secure_issue_tracker.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
