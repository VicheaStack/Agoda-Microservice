package com.example.booking_service.controllerReactive;

import com.example.booking_service.dto.RoomBookingSnapshotDTO;
import com.example.booking_service.dto.RoomDTO;
import com.example.booking_service.serviceImpl.RoomServiceReactive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Validated
@Slf4j
@RestController
@RequestMapping("/api/v1/room")
public class RoomControllerReactive {

    private final RoomServiceReactive roomService;

    public RoomControllerReactive(RoomServiceReactive roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<RoomDTO> createRoom(@RequestBody RoomDTO dto) {
        return roomService.createRoom(dto)
                .doOnSuccess(room ->
                        log.info("Room created successfully: {}", room.getRoomNumber()))
                .doOnError(error ->
                        log.error("Error creating room: {}", error.getMessage()));
    }

    @GetMapping("/{id}")
    public Mono<RoomBookingSnapshotDTO> getRoomById(@PathVariable Long id) {
        return roomService.getRoomById(id)
                .doOnSuccess(room ->
                        log.info("Retrieved room: {}", room.getRoomNumber()))
                .doOnError(error ->
                        log.error("Error retrieving room {}: {}", id, error.getMessage()));
    }

    @PutMapping("/{id}")
    public Mono<RoomDTO> updateRoom(@PathVariable Long id, @RequestBody RoomDTO dto) {
        return roomService.updateRoom(dto, id)
                .doOnSuccess(room ->
                        log.info("Updated room: {}", room.getRoomNumber()))
                .doOnError(error ->
                        log.error("Error updating room {}: {}", id, error.getMessage()));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteRoom(@PathVariable Long id) {
        return roomService.deleteRoom(id)
                .doOnSuccess(unused ->
                        log.info("Deleted room with ID: {}", id))
                .doOnError(error ->
                        log.error("Error deleting room {}: {}", id, error.getMessage()));
    }

    @GetMapping("/availability")
    public Mono<Page<RoomBookingSnapshotDTO>> checkAvailability(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return roomService.checkRoomAvailability(page, size)
                .doOnSuccess(pageResult ->
                        log.info("Found {} available rooms", pageResult.getTotalElements()))
                .doOnError(error ->
                        log.error("Error checking availability: {}", error.getMessage()));
    }
}