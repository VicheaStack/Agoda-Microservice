package com.group.learn.service;

import com.group.learn.dto.LoyaltyDTO;
import com.group.learn.entity.LoyaltyTransaction;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private final WebClient webClient;

    public UserService(WebClient webClient) {
        this.webClient = webClient;   // Correct because you have a WebClient bean
    }

    public Mono<LoyaltyTransaction> loyaltyTransactionMono(LoyaltyDTO dto){
        return webClient
                .post()
                .uri("/api/v1/loyalty")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dto)
                .retrieve()
                .bodyToMono(LoyaltyTransaction.class);
    }

    public Mono<LoyaltyTransaction> getLoyaltyById(Long id){
        return webClient
                .get()
                .uri("/api/v1/loyalty/{id}", id)
                .retrieve()
                .bodyToMono(LoyaltyTransaction.class);
    }

    public Mono<LoyaltyTransaction> updateLoyalty(Long id, LoyaltyDTO dto){
        return webClient
                .put()
                .uri("/api/v1/loyalty/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dto)
                .retrieve()
                .bodyToMono(LoyaltyTransaction.class);
    }

    public Mono<Void> deleteLoyalty(Long id){
        return webClient
                .delete()
                .uri("/api/v1/loyalty/{id}", id)
                .retrieve()
                .bodyToMono(Void.class);
    }

}
