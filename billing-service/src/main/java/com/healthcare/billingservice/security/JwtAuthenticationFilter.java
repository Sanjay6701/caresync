package com.healthcare.billingservice.security;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {

            String token = header.substring(7);
            try {
                String email = jwtUtil.extractEmail(token);
                String role = jwtUtil.extractRole(token);

                // Handle null or empty roles with default PATIENT role
                if (role == null || role.trim().isEmpty()) {
                    role = "PATIENT";
                }

                String finalRole =
                        role.startsWith("ROLE_") ? role : "ROLE_" + role;

                if (email != null
                        && SecurityContextHolder.getContext().getAuthentication() == null) {

                    List<SimpleGrantedAuthority> authorities =
                            Collections.singletonList(
                                    new SimpleGrantedAuthority(finalRole.toUpperCase()));

                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(
                                    email, null, authorities);

                    SecurityContextHolder.getContext().setAuthentication(auth);
                    System.out.println("✅ JWT Auth Set - Email: " + email + ", Role: " + finalRole);
                }
            } catch (Exception e) {
                //SecurityContextHolder.clearContext(); // invalid token
            	System.err.println("❌ JWT Auth Error: " + e.getMessage());
            	e.printStackTrace();
            }
        }

        chain.doFilter(request, response);
    }
}

