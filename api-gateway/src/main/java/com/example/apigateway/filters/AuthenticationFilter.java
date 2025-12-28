package com.example.apigateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Component
public class AuthenticationFilter implements GlobalFilter, Ordered {

    private static final String AUTH_HEADER = "Authorization";
    private static final String API_KEY_HEADER = "X-API-Key";
    private static final List<String> PUBLIC_PATHS = List.of(
            "/api/auth/login",
            "/api/auth/register",
            "/actuator/health",
            "/actuator/info"
    );

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getPath().toString();

        // 1. Skip authentication for public paths
        if (isPublicPath(path)) {
            log.debug("Skipping auth for public path: {}", path);
            return chain.filter(exchange);
        }

        // 2. Check for API Key (service-to-service)
        String apiKey = request.getHeaders().getFirst(API_KEY_HEADER);
        if (apiKey != null && isValidApiKey(apiKey)) {
            log.debug("Valid API key found for path: {}", path);
            return chain.filter(exchange);
        }

        // 3. Check for JWT token
        String authHeader = request.getHeaders().getFirst(AUTH_HEADER);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.warn("No valid authorization header for path: {}", path);
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String token = authHeader.substring(7);
        if (!isValidToken(token)) {
            log.warn("Invalid token for path: {}", path);
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // 4. Extract user info and add to headers
        String username = extractUsername(token);
        ServerHttpRequest modifiedRequest = request.mutate()
                .header("X-User-Id", username)
                .header("X-Roles", extractRoles(token))
                .build();

        log.debug("User {} authenticated for path: {}", username, path);
        return chain.filter(exchange.mutate().request(modifiedRequest).build());
    }

    private boolean isPublicPath(String path) {
        return PUBLIC_PATHS.stream().anyMatch(path::startsWith);
    }

    private boolean isValidApiKey(String apiKey) {
        // In production, validate against database or config
        return "SECRET-API-KEY-123".equals(apiKey);
    }

    private boolean isValidToken(String token) {
        // In production, validate JWT with auth service
        // This is simplified - use a real JWT library
        return token.length() > 10;
    }

    private String extractUsername(String token) {
        // Extract from JWT token
        return "user123";
    }

    private String extractRoles(String token) {
        // Extract roles from JWT token
        return "USER";
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1; // Run after logging
    }
}