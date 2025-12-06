package com.example.booking_service.controller;

import com.example.booking_service.dto.ReviewsDto;
import com.example.booking_service.entity.Reviews;
import com.example.booking_service.mapper.ReviewsMapper;
import com.example.booking_service.service.ReviewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/reviews")
public class ReviewsController {

    private final ReviewsService reviewsService;
    private final ReviewsMapper mapper;

    public ReviewsController(ReviewsService reviewsService, ReviewsMapper mapper) {
        this.reviewsService = reviewsService;
        this.mapper = mapper;
    }

    // ========================= CREATE =========================
    @PostMapping
    public ResponseEntity<ReviewsDto> create(@Validated @RequestBody ReviewsDto dto) {
        Reviews entity = mapper.toEntity(dto);
        Reviews created = reviewsService.create(entity);
        return ResponseEntity.ok(mapper.toDto(created));
    }

    // ========================= UPDATE =========================
    @PutMapping("/{id}")
    public ResponseEntity<ReviewsDto> update(@Validated @PathVariable Long id,
                                             @RequestBody ReviewsDto dto) {
        Reviews entity = mapper.toEntity(dto);
        Reviews updated = reviewsService.update(entity, id);
        return ResponseEntity.ok(mapper.toDto(updated));
    }

    // ========================= GET BY ID =========================
    @GetMapping("/{id}")
    public ResponseEntity<ReviewsDto> findById(@PathVariable Long id) {
        Reviews found = reviewsService.getById(id);
        return ResponseEntity.ok(mapper.toDto(found));
    }

    // ========================= DELETE =========================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reviewsService.delete(id);
        log.info("Review with id {} has been deleted", id);
        return ResponseEntity.noContent().build();
    }
}
