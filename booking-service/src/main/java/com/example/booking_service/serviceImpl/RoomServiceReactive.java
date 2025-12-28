package com.example.booking_service.serviceImpl;

import com.example.booking_service.dto.RoomBookingSnapshotDTO;
import com.example.booking_service.dto.RoomDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class RoomServiceReactive {

    private final WebClient webClient;

    public RoomServiceReactive(@Qualifier("roomServiceGateway") WebClient webClient) {
        this.webClient = webClient;
    }

    // Create room
    public Mono<RoomDTO> createRoom(RoomDTO request) {
        return webClient.post()
                .uri("/api/v1/room")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(RoomDTO.class)
                .timeout(Duration.ofSeconds(3));
    }

    // Check room availability - GET with query params
    public Mono<Page<RoomBookingSnapshotDTO>> checkRoomAvailability(int page, int size) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/room/availability")
                        .queryParam("page", page)
                        .queryParam("size", size)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Page<RoomBookingSnapshotDTO>>() {})
                .timeout(Duration.ofSeconds(3));
    }

    // Update room - returns RoomDTO
    public Mono<RoomDTO> updateRoom(RoomDTO request, Long id) {
        return webClient.put()
                .uri("/api/v1/room/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(RoomDTO.class)  // Changed to RoomDTO
                .timeout(Duration.ofSeconds(3));
    }

    // Get room by ID - returns RoomDTO
    public Mono<RoomBookingSnapshotDTO> getRoomById(Long roomId) {
        return webClient.get()
                .uri("/api/v1/room/{id}", roomId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(RoomBookingSnapshotDTO.class)  // Changed to RoomDTO
                .timeout(Duration.ofSeconds(3));
    }

    // Delete room
    public Mono<Void> deleteRoom(Long roomId) {
        return webClient.delete()
                .uri("/api/v1/room/{id}", roomId)
                .retrieve()
                .bodyToMono(Void.class)  // ✅ Correct for empty response
                .timeout(Duration.ofSeconds(3));
    }

}