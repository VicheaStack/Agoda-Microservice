package com.hotel.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebConfig {

    @Bean
    @LoadBalanced
    public WebClient.Builder LoadBalancedWebClientBuilder(){
        return WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    }

    @Bean
    public WebClient guestServiceGateway(@LoadBalanced WebClient.Builder builder) {
        return builder
                .baseUrl("http://GUEST-SERVICE")
                .build();
    }

    @Bean
    public WebClient loyaltyServiceGateway(@LoadBalanced WebClient.Builder builder) {
        return builder
                .baseUrl("http://LOYALTY-SERVICE")
                .build();
    }

    @Bean
    public WebClient roomServiceGateway(@LoadBalanced WebClient.Builder builder){
        return builder
                .baseUrl("http://BOOKING-SERVICE")
                .build();
    }
}
