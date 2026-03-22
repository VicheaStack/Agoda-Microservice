package com.example.booking_service.serviceImpl;

import com.example.booking_service.dto.PageResponse;
import com.example.booking_service.dto.RoomAvailabilityPage;
import com.example.booking_service.dto.RoomBookingSnapshotDTO;
import com.example.booking_service.dto.RoomDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@Service
public class RoomServiceReactive {

    private final WebClient webClient;

    public RoomServiceReactive(@Qualifier("roomWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    // Create room
    public Mono<RoomDTO> createRoom(RoomDTO request) {
        return webClient.post()
                .uri("/rooms")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(RoomDTO.class)
                .timeout(Duration.ofSeconds(3));
    }

    // Check room availability - GET with query params
    // Change the method signature to return PageImpl instead of Page
    public Flux<RoomBookingSnapshotDTO> checkRoomAvailability(int page, int size) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/rooms/availability")
                        .queryParam("page", page)
                        .queryParam("size", size)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(RoomAvailabilityPage.class)  // parse outer wrapper
                .timeout(Duration.ofSeconds(3))
                .flatMapMany(pageData -> Flux.fromIterable(pageData.getContent()));
    }

    // Update room - returns RoomDTO
    public Mono<RoomDTO> updateRoom(RoomDTO request, Long id) {
        return webClient.put()
                .uri("/rooms/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(RoomDTO.class)  // Changed to RoomDTO
                .timeout(Duration.ofSeconds(3));
    }

    // Get room by ID - returns RoomDTO
    public Mono<RoomBookingSnapshotDTO> getRoomById(Long roomId) {
        return webClient.get()
                .uri("/room/{id}", roomId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(RoomBookingSnapshotDTO.class)  // Changed to RoomDTO
                .timeout(Duration.ofSeconds(3));
    }

    // Delete room
    public Mono<Void> deleteRoom(Long roomId) {
        return webClient.delete()
                .uri("/rooms/{id}", roomId)
                .retrieve()
                .bodyToMono(Void.class)  // ✅ Correct for empty response
                .timeout(Duration.ofSeconds(3));
    }

}