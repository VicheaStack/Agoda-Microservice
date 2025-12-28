package com.example.apigateway.filters;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

@Slf4j
@Component
public class ServiceDiscoveryFilter implements GlobalFilter, Ordered {
    
    private final EurekaClient eurekaClient;
    
    public ServiceDiscoveryFilter(EurekaClient eurekaClient) {
        this.eurekaClient = eurekaClient;
    }
    
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        
        if (route != null) {
            URI routeUri = route.getUri();
            String scheme = routeUri.getScheme();
            
            // Only process lb:// routes
            if ("lb".equals(scheme)) {
                String serviceName = routeUri.getHost().toUpperCase();
                log.debug("Resolving service: {}", serviceName);
                
                // Get service instance from Eureka
                InstanceInfo instance = eurekaClient.getNextServerFromEureka(serviceName, false);
                
                if (instance != null) {
                    String serviceUrl = instance.getHomePageUrl();
                    log.debug("Resolved {} to {}", serviceName, serviceUrl);
                    
                    // You can modify the request based on service instance metadata
                    if (instance.getMetadata().containsKey("version")) {
                        String version = instance.getMetadata().get("version");
                        exchange.getRequest().mutate()
                                .header("X-Service-Version", version);
                    }
                } else {
                    log.warn("No instances found for service: {}", serviceName);
                }
            }
        }
        
        return chain.filter(exchange);
    }
    
    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE - 1; // Run before sending to service
    }
}