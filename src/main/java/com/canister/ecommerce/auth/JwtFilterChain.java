package com.canister.ecommerce.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor
@Service
public class JwtFilterChain extends OncePerRequestFilter {

    private final JwtConfig jwtConfig;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Auth");

        if(token != null && token.startsWith("Bearer ")){
            String jwtToken = token.substring(7);
            String userId = jwtConfig.validateAuthToken(jwtToken);
            if (SecurityContextHolder.getContext().getAuthentication() != null){
                filterChain.doFilter(request, response);
            }
        }
    }
}
