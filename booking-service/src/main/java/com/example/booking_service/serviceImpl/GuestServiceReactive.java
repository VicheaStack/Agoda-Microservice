package com.example.booking_service.serviceImpl;

import com.example.booking_service.dto.GuestRequestDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class GuestServiceReactive {

    private final WebClient webClient;

    public GuestServiceReactive(@Qualifier("guestServiceGateway") WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<GuestRequestDTO> createGuest(GuestRequestDTO guestRequestDTO) {
        return webClient.post()
                .uri("/api/v1/guests")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(guestRequestDTO)
                .retrieve()
                .bodyToMono(GuestRequestDTO.class)
                .timeout(Duration.ofSeconds(3));
    }

    public Mono<GuestRequestDTO> updateGuest(GuestRequestDTO guestRequestDTO, Long id){
        return webClient.put()
                .uri("/api/v1/guests/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(guestRequestDTO)
                .retrieve()
                .bodyToMono(GuestRequestDTO.class)
                .timeout(Duration.ofSeconds(3));
    }

    public Mono<GuestRequestDTO> getGuestById(Long id){
        return webClient.get()
                .uri("api/v1/search/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(GuestRequestDTO.class)
                .timeout(Duration.ofSeconds(3));
    }

    public Mono<Void> deleteGuest(Long guestId){
        return webClient.delete()
                .uri("/api/v1/guests/{id}", guestId)
                .retrieve()
                .bodyToMono(Void.class);
    }
}
