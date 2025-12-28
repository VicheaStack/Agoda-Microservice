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

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class RateLimitingFilter implements GlobalFilter, Ordered {

    // Store request counts: key = "IP:path", value = request count
    private final Map<String, RateLimitData> rateLimitMap = new ConcurrentHashMap<>();

    private static class RateLimitData {
        private final AtomicInteger requestCount = new AtomicInteger(0);
        private long windowStartTime;

        RateLimitData() {
            this.windowStartTime = System.currentTimeMillis();
            this.requestCount.set(0);
        }

        boolean allowRequest() {
            long currentTime = System.currentTimeMillis();

            // Reset if 1 second has passed
            if (currentTime - windowStartTime > 1000) {
                windowStartTime = currentTime;
                requestCount.set(0);
            }

            // Check if under limit (10 requests per second)
            return requestCount.incrementAndGet() <= 10;
        }
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        // Get client IP
        String clientIp = "unknown";
        if (request.getRemoteAddress() != null && request.getRemoteAddress().getAddress() != null) {
            clientIp = request.getRemoteAddress().getAddress().getHostAddress();
        }

        String path = request.getPath().value();
        String key = clientIp + ":" + path;

        // Get or create rate limit data for this client+path
        RateLimitData rateData = rateLimitMap.computeIfAbsent(key, k -> new RateLimitData());

        // Check rate limit
        if (rateData.allowRequest()) {
            log.debug("Request allowed for {} on path {}", clientIp, path);
            return chain.filter(exchange);
        } else {
            log.warn("Rate limit exceeded for {} on path {}", clientIp, path);

            // Set 429 Too Many Requests status
            exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);

            // Add rate limit headers
            exchange.getResponse().getHeaders().add("X-Rate-Limit-Limit", "10");
            exchange.getResponse().getHeaders().add("X-Rate-Limit-Retry-After", "1");
            exchange.getResponse().getHeaders().add("Content-Type", "application/json");

            // Return JSON error response
            String errorJson = """
                {
                    "error": "Too Many Requests",
                    "message": "Rate limit exceeded. Maximum 10 requests per second.",
                    "status": 429,
                    "path": "%s"
                }
                """.formatted(path);

            return exchange.getResponse().writeWith(
                    Mono.just(exchange.getResponse().bufferFactory().wrap(errorJson.getBytes()))
            );
        }
    }

    @Override
    public int getOrder() {
        // Run after authentication filters but before other filters
        return Ordered.LOWEST_PRECEDENCE - 1;
    }
}