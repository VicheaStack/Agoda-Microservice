package com.example.Loyalty_Service.controller;

import com.example.Loyalty_Service.dto.LoyaltyDTO;
import com.example.Loyalty_Service.mapper.LoyaltyMapper;
import com.example.Loyalty_Service.model.Loyalty;
import com.example.Loyalty_Service.serviceImpl.LoyaltyServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/loyalty")
public class LoyaltyController {

    private final LoyaltyServiceImpl service;
    private final LoyaltyMapper mapper;

    public LoyaltyController(LoyaltyServiceImpl service, LoyaltyMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    // ---------------- CREATE ----------------
    @PostMapping
    public ResponseEntity<LoyaltyDTO> create(@Validated @RequestBody LoyaltyDTO dto) {
        Loyalty entity = mapper.toEntity(dto);
        Loyalty saved = service.create(entity);
        LoyaltyDTO response = mapper.toDto(saved);
        return ResponseEntity.ok(response);
    }

    // ---------------- UPDATE ----------------
    @PutMapping("/{id}")
    public ResponseEntity<LoyaltyDTO> update(@PathVariable Long id, @RequestBody LoyaltyDTO dto) {
        Loyalty entity = mapper.toEntity(dto);
        Loyalty updated = service.update(id, entity);
        LoyaltyDTO response = mapper.toDto(updated);
        return ResponseEntity.ok(response);
    }

    // ---------------- FIND BY ID ----------------
    @GetMapping("/{id}")
    public ResponseEntity<LoyaltyDTO> find(@PathVariable Long id) {
        Loyalty loyalty = service.getById(id);
        LoyaltyDTO response = mapper.toDto(loyalty);
        return ResponseEntity.ok(response);
    }

    // ---------------- DELETE ----------------
    @DeleteMapping("/{id}")
    public ResponseEntity<LoyaltyDTO> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
