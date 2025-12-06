package com.example.booking_service.controller;

import com.example.booking_service.dto.PromotionsDto;
import com.example.booking_service.entity.Promotions;
import com.example.booking_service.mapper.PromotionsMapper;
import com.example.booking_service.service.PromotionsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/promotions")
public class PromotionController {

    private final PromotionsService promotionsService;
    private final PromotionsMapper mapper;

    public PromotionController(PromotionsService promotionsService, PromotionsMapper mapper) {
        this.promotionsService = promotionsService;
        this.mapper = mapper;
    }

    // ========================= CREATE =========================
    @PostMapping
    public ResponseEntity<PromotionsDto> create(@Validated @RequestBody PromotionsDto dto) {
        Promotions entity = mapper.toEntity(dto);
        Promotions created = promotionsService.create(entity);
        return ResponseEntity.ok(mapper.toDto(created));
    }

    // ========================= UPDATE =========================
    @PutMapping("/{id}")
    public ResponseEntity<PromotionsDto> update(@Validated @PathVariable Long id,
                                                @RequestBody PromotionsDto dto) {
        Promotions entity = mapper.toEntity(dto);
        Promotions updated = promotionsService.update(entity, id);
        return ResponseEntity.ok(mapper.toDto(updated));
    }

    // ========================= GET BY ID =========================
    @GetMapping("/{id}")
    public ResponseEntity<PromotionsDto> findById(@PathVariable Long id) {
        Promotions found = promotionsService.getById(id);
        return ResponseEntity.ok(mapper.toDto(found));
    }

    // ========================= DELETE =========================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByID(@PathVariable Long id) {
        promotionsService.delete(id);
        log.info("Promotion with id {} has been deleted", id);
        return ResponseEntity.noContent().build();
    }
}
