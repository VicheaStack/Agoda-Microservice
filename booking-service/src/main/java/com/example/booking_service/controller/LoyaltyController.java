package com.example.booking_service.controller;

import com.example.booking_service.dto.LoyaltyRequestDTO;
import com.example.booking_service.serviceImpl.LoyaltyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/loyalty")
@RequestMapping
public class LoyaltyController {

    private final LoyaltyService loyaltyService;

    public LoyaltyController(LoyaltyService loyaltyService) {
        this.loyaltyService = loyaltyService;

    }

    @GetMapping("/{id}")
    public ResponseEntity<LoyaltyRequestDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(loyaltyService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LoyaltyRequestDTO> update(@PathVariable Long id, @RequestBody LoyaltyRequestDTO loyaltyRequestDTO){
        return ResponseEntity.ok(loyaltyService.update(id, loyaltyRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoyaltyRequestDTO> deleteById(@PathVariable Long id){
        return ResponseEntity.ok(loyaltyService.findById(id));
    }

}
