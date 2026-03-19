package com.example.apigateway.filters;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class CorrelationIdFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String correlationId = UUID.randomUUID().toString();

        // 1. Mutate the request with the CORRECT header name (no leading space)
        ServerWebExchange updatedExchange = exchange.mutate()
                .request(r -> r.header("X-Correlation-ID", correlationId))
                .build();

        // 2. Add to response headers
        updatedExchange.getResponse().getHeaders().add("X-Correlation-ID", correlationId);

        return chain.filter(updatedExchange);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
