package com.example.Loyalty_Service.controller;

import com.example.Loyalty_Service.dto.LoyaltyDTO;
import com.example.Loyalty_Service.dto.LoyaltyRequestDTO;
import com.example.Loyalty_Service.mapper.LoyaltyMapper;
import com.example.Loyalty_Service.model.Loyalty;
import com.example.Loyalty_Service.service.LoyaltyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/loyalty")
public class LoyaltyController {

    private final LoyaltyService service;
    private final LoyaltyMapper mapper;

    public LoyaltyController(LoyaltyService service, LoyaltyMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<LoyaltyDTO> create(@Validated @RequestBody LoyaltyRequestDTO dto) {
        Loyalty entity = mapper.toEntity(dto);
        Loyalty saved = service.create(entity);
        LoyaltyDTO response = mapper.toDTO(saved);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoyaltyDTO> update(@PathVariable Long id, @RequestBody LoyaltyRequestDTO dto) {
        Loyalty entity = mapper.toEntity(dto);
        Loyalty updated = service.update(id, entity);
        LoyaltyDTO response = mapper.toDTO(updated);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoyaltyDTO> find(@PathVariable Long id) {
        Loyalty loyalty = service.getById(id);
        LoyaltyDTO dto = mapper.toDTO(loyalty);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
