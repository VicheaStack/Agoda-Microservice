package com.group.learn.service;

import com.group.learn.dto.LoyaltyDTO;
import com.group.learn.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private final WebClient webClient;

    public UserService(WebClient webClient) {
        this.webClient = webClient;
    }

    private Mono<? extends Throwable> handleError(ClientResponse response) {
        return response.bodyToMono(String.class)
                .flatMap(errorBody -> {
                    if (response.statusCode().is4xxClientError()) {
                        return Mono.error(new ResourceNotFoundException(errorBody));
                    }
                    if (response.statusCode().is5xxServerError()) {
                        return Mono.error(new RuntimeException("Loyalty service down"));
                    }
                    return response.createException().flatMap(Mono::error);
                });
    }

    public Mono<LoyaltyDTO> loyaltyTransactionMono(LoyaltyDTO dto) {
        return webClient.post()
                .uri("/api/v1/loyalty")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dto)
                .retrieve()
                .onStatus(HttpStatusCode::isError, this::handleError)
                .bodyToMono(LoyaltyDTO.class);
    }

    public Mono<LoyaltyDTO> getLoyaltyById(Long id){
        return webClient.get()
                .uri("/api/v1/loyalty/{id}", id)
                .retrieve()
                .onStatus(HttpStatusCode::isError, this::handleError)
                .bodyToMono(LoyaltyDTO.class);
    }

    public Mono<LoyaltyDTO> updateLoyalty(Long id, LoyaltyDTO dto){
        return webClient.put()
                .uri("/api/v1/loyalty/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dto)
                .retrieve()
                .onStatus(HttpStatusCode::isError, this::handleError)
                .bodyToMono(LoyaltyDTO.class);
    }

    public Mono<Void> deleteLoyalty(Long id){
        return webClient.delete()
                .uri("/api/v1/loyalty/{id}", id)
                .retrieve()
                .onStatus(HttpStatusCode::isError, this::handleError)
                .bodyToMono(Void.class);
    }
}
