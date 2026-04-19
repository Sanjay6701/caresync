package com.healthcare.billingservice.security;

import java.security.Key;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private final String SECRET =
            "HealthcareAuthServiceJwtSecretKey2026"; // SAME as Auth Service

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    // OPTIONAL (if needed later)
    public Long extractPid(String token) {
        return extractAllClaims(token).get("pid", Long.class);
    }

    public Long extractDid(String token) {
        return extractAllClaims(token).get("did", Long.class);
    }
}
