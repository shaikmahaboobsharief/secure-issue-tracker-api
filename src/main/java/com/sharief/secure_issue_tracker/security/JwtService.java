package com.sharief.secure_issue_tracker.security;

import com.sharief.secure_issue_tracker.user.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    public String generateToken(User user) {
        log.debug("Generating JWT token for user: {}", user.getEmail());
        String token = Jwts.builder()
                .subject(user.getEmail())
                .claim("role", user.getRole().name())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                .compact();

        log.info("JWT token generated for user: {}", user.getEmail());

        return token;
    }

    public String extractEmail(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
        } catch (Exception e) {
            log.warn("Failed to extract email from JWT: {}", e.getMessage());
            return null;
        }
    }

    public boolean isTokenValid(String token, User user) {
        String email = extractEmail(token);

        boolean isValid = email != null && email.equals(user.getEmail());

        if (!isValid) {
            log.warn("Invalid JWT token for user: {}", user.getEmail());
        }

        return isValid;
    }
}