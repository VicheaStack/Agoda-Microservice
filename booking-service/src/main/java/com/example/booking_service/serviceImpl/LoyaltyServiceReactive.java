package com.example.booking_service.serviceImpl;

import com.example.booking_service.dto.LoyaltyRequestDTO;
import com.example.booking_service.entity.ErrorResponse;
import com.example.booking_service.execption.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class LoyaltyServiceReactive {

    private final WebClient webClient;

    public LoyaltyServiceReactive(@Qualifier("loyaltyServiceGateway") WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<LoyaltyRequestDTO> dataSend(LoyaltyRequestDTO loyaltyRequestDTO){
        return webClient.post()
                .uri("/api/v1/loyalty")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(loyaltyRequestDTO)
                .retrieve()
                .bodyToMono(LoyaltyRequestDTO.class)
                .timeout(Duration.ofSeconds(3));  // timeout And try agian
    }

    public Mono<LoyaltyRequestDTO> update(LoyaltyRequestDTO loyaltyRequestDTO, Long guestId){
        return webClient.put()
                .uri("/api/v1/loyalty/{guestId}", guestId)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(loyaltyRequestDTO)
                .retrieve()
                // HttpStatusCode::is4xxClientError handle error for api request build in one
                // then we throw Error Handle for api update methoed Put
                //
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    return response.bodyToMono(ErrorResponse.class) // read what bodyErrorResponse convert into one Objext in Mono
                            .flatMap(error -> Mono.error(new ResourceNotFoundException(error.getMessage())));
                })
                // HttpStatusCode::is5xxClientError handle error General for server not found
                // then we throw EndPoint not found
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> {
                    return Mono.error(new ResourceNotFoundException("not "));
                })
                .bodyToMono(LoyaltyRequestDTO.class)
                .timeout(Duration.ofSeconds(3));
    }

    // make the parameter what we want
    public Mono<Void> delete(Long guestId){
        return webClient
                // if we send delete it don't have body as Json
                .delete()
                .uri("/api/v1/loyalty/{id}", guestId)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                    return Mono.error(new ResourceNotFoundException("Reception not found"));
                })
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> {
                    return Mono.error(new ResourceNotFoundException("Loyalty are down"));
                })
                .bodyToMono(Void.class);
    }
}
