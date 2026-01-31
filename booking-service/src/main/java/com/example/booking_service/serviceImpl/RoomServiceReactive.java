package com.example.booking_service.serviceImpl;

import com.example.booking_service.dto.PageResponse;
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
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class RoomServiceReactive {

    private final WebClient webClient;

    public RoomServiceReactive(@Qualifier("roomWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    // Create room
    public Mono<RoomDTO> createRoom(RoomDTO request) {
        return webClient.post()
                .uri("/room")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(RoomDTO.class)
                .timeout(Duration.ofSeconds(3));
    }

    // Check room availability - GET with query params
    // Change the method signature to return PageImpl instead of Page
    public Mono<Page<RoomBookingSnapshotDTO>> checkRoomAvailability(int page, int size) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/room/availability")
                        .queryParam("page", page)
                        .queryParam("size", size)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<PageResponse<RoomBookingSnapshotDTO>>() {})
                .<Page<RoomBookingSnapshotDTO>>map(pageResponse ->
                        new PageImpl<>(
                                pageResponse.getContent(),
                                PageRequest.of(pageResponse.getCurrentPage(), pageResponse.getSize()),
                                pageResponse.getTotalElement()
                        )
                )
                .timeout(Duration.ofSeconds(3));
    }


    // Update room - returns RoomDTO
    public Mono<RoomDTO> updateRoom(RoomDTO request, Long id) {
        return webClient.put()
                .uri("/room/{id}", id)
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
                .uri("/room/{id}", roomId)
                .retrieve()
                .bodyToMono(Void.class)  // ✅ Correct for empty response
                .timeout(Duration.ofSeconds(3));
    }

}