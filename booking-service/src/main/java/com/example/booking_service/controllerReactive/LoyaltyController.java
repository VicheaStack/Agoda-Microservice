package com.example.booking_service.controllerReactive;

import com.example.booking_service.dto.LoyaltyRequestDTO;
import com.example.booking_service.entity.LoyaltyTransaction;
import com.example.booking_service.serviceImpl.LoyaltyServiceReactive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Validated
@Slf4j
@RestController
@RequestMapping("/api/v1/loyalty")
public class LoyaltyController {

    private final LoyaltyServiceReactive loyaltyServiceReactive;

    public LoyaltyController(LoyaltyServiceReactive loyaltyServiceReactive) {
        this.loyaltyServiceReactive = loyaltyServiceReactive;
    }

    @PostMapping
    public Mono<LoyaltyRequestDTO> createLoyalty(@RequestBody LoyaltyRequestDTO dto) {
        return loyaltyServiceReactive.dataSend(dto)
                .doOnSuccess(tx ->
                        log.info("Loyalty transaction created with ID: {}",
                                tx.guestId()))
                .doOnError(error ->
                        log.error("Error creating loyalty transaction", error));
    }

    @PutMapping("/{guestId}")
    public Mono<ResponseEntity<LoyaltyRequestDTO>> updateLoyalty(@RequestBody LoyaltyRequestDTO dto,
                                                                 @PathVariable Long guestId) {
        return loyaltyServiceReactive.update(dto, guestId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .doOnSuccess(response -> {
                    log.info("Loyalty transaction updated for Guest ID: {}", guestId);
                }).doOnError(error -> {
                    log.error("Error updating loyalty transaction for Guest ID: {}", guestId, error);
                });
    }

    @DeleteMapping("/{guestId}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable Long guestId){
        return loyaltyServiceReactive.delete(guestId)
                .then(Mono.just(ResponseEntity.ok().<Void>build()))
                .doOnSuccess(response ->
                        log.info("Loyalty transaction deleted for Guest ID: {}", guestId))
                .doOnError(error ->
                        log.error("Error deleting loyalty transaction for Guest ID: {}", guestId, error));
    }
}



