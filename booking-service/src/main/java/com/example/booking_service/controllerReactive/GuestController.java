package com.example.booking_service.controllerReactive;

import com.example.booking_service.dto.GuestRequestDTO;
import com.example.booking_service.serviceImpl.GuestServiceReactive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Validated
@Slf4j
@RestController
@RequestMapping("/api/v1/guests")  // Added /v1 for versioning
public class GuestController {

    private final GuestServiceReactive guestService;

    public GuestController(GuestServiceReactive guestService) {
        this.guestService = guestService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<GuestRequestDTO> createGuest(@RequestBody GuestRequestDTO requestDTO) {
        return guestService.createGuest(requestDTO)
                .doOnSuccess(guest ->
                        log.info("Successfully created guest with ID: {}", guest.id()))
                .doOnError(error ->
                        log.error("Failed to create guest: {}", error.getMessage()));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<GuestRequestDTO>> updateGuest(
            @PathVariable Long id,
            @RequestBody GuestRequestDTO requestDTO) {
        return guestService.updateGuest(requestDTO, id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .doOnSuccess(response -> {
                    if (response.getStatusCode().is2xxSuccessful()) {
                        log.info("Updated guest with ID: {}", id);
                    } else {
                        log.warn("Guest not found for update with ID: {}", id);
                    }
                });
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<GuestRequestDTO>> getGuestById(@PathVariable Long id) { // Fixed: Added @PathVariable
        return guestService.getGuestById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .doOnSuccess(response -> {
                    if (response.getStatusCode().is2xxSuccessful()) {
                        log.info("Found guest with ID: {}", id);
                    } else {
                        log.warn("Guest not found with ID: {}", id);
                    }
                });
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteGuest(@PathVariable Long id) {
        return guestService.deleteGuest(id)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()))
                .doOnSuccess(response ->
                        log.info("Deleted guest with ID: {}", id))
                .doOnError(error ->
                        log.error("Failed to delete guest with ID {}: {}", id, error.getMessage()))
                .onErrorResume(error ->
                        Mono.just(ResponseEntity.notFound().<Void>build()));
    }
}