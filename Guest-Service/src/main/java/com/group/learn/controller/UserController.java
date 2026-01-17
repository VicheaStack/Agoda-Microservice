package com.group.learn.controller;

import com.group.learn.dto.LoyaltyDTO;
import com.group.learn.exception.ResourceNotFoundException;
import com.group.learn.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Validated
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // -------------------- CREATE --------------------
    @PostMapping("/loyalty")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<LoyaltyDTO> createLoyalty(@Valid @RequestBody LoyaltyDTO dto) {
        return userService.loyaltyTransactionMono(dto)
                .doOnSuccess(tx -> log.info("Successfully created loyalty transaction: {}", tx.id()))
                .doOnError(error -> log.error("Failed to create loyalty transaction", error));
    }

    // -------------------- READ --------------------
    @GetMapping("/loyalty/{id}")
    public Mono<ResponseEntity<LoyaltyDTO>> getLoyaltyById(@PathVariable Long id) {
        return userService.getLoyaltyById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .doOnSuccess(response -> {
                    if (response.getStatusCode().is2xxSuccessful()) {
                        log.info("Found loyalty transaction with ID: {}", id);
                    } else {
                        log.warn("Loyalty transaction not found with ID: {}", id);
                    }
                });
    }

    // -------------------- UPDATE --------------------
    @PutMapping("/loyalty/{id}")
    public Mono<ResponseEntity<LoyaltyDTO>> updateLoyalty(
            @PathVariable Long id,
            @Valid @RequestBody LoyaltyDTO dto
    ) {
        return userService.updateLoyalty(id, dto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .doOnSuccess(response -> {
                    if (response.getStatusCode().is2xxSuccessful()) {
                        log.info("Updated loyalty transaction with ID: {}", id);
                    } else {
                        log.warn("Loyalty transaction not found for update with ID: {}", id);
                    }
                })
                .doOnError(error -> log.error("Failed to update loyalty transaction with ID: {}", id, error));
    }

    // -------------------- DELETE --------------------
    @DeleteMapping("/loyalty/{id}")
    public Mono<ResponseEntity<Void>> deleteLoyalty(@PathVariable Long id) {
        return userService.deleteLoyalty(id)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()))
                .doOnSuccess(v -> log.info("Deleted loyalty transaction with ID: {}", id))
                .doOnError(e -> log.error("Failed to delete loyalty transaction with ID: {}", id, e))
                .onErrorResume(ResourceNotFoundException.class, e ->
                        Mono.just(ResponseEntity.notFound().build()));
    }

}
