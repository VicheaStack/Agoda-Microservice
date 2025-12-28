package com.example.apigateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;


@Component
public class RequestLoggingFilter implements GlobalFilter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);
    private static final String CORRELATION_ID = "X-Correlation-Id";
    private static final String START_TIME = "startTime";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
       // Start timer
        exchange.getAttributes().put(START_TIME, System.currentTimeMillis());

        ServerHttpRequest request = exchange.getRequest();
        String correlationId = getOrGenerateCorrelationId(request);

        MDC.put(CORRELATION_ID, correlationId);

        ServerHttpRequest modifiedRequest = request.mutate()
                .header(CORRELATION_ID, correlationId)
                .build();

        logRequest(request, correlationId);

        return chain.filter(exchange.mutate().request(modifiedRequest).build())
                .doFinally(signalType -> logResponse(exchange, correlationId));
    }

    private String getOrGenerateCorrelationId(ServerHttpRequest request){
        String correlationId = request.getHeaders().getFirst(CORRELATION_ID);
        return correlationId != null ? correlationId : UUID.randomUUID().toString();
    }

    private void logRequest (ServerHttpRequest request, String correlationId){
        logger.info("""
            ==== GATEWAY REQUEST ====
            Correlation ID: {}
            Method: {}
            Path: {}
            Headers: {}
            Query Params: {}
            Remote Address: {}
            ==========================""",
                correlationId,
                request.getMethod(),
                request.getPath(),
                request.getHeaders(),
                request.getQueryParams(),
                request.getRemoteAddress()
        );
    }

    private void logResponse(ServerWebExchange exchange, String correlationId) {
        Long startTime = exchange.getAttribute(START_TIME);
        if (startTime != null) {
            long duration = System.currentTimeMillis() - startTime;
            logger.info("""
                    === GATEWAY RESPONSE ===
                    Correlation ID: {}
                    Status: {}
                    Duration: {} ms
                    ==================
                    """,
                    correlationId,
                    exchange.getResponse().getStatusCode(),
                    duration);
        }
    }


    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}