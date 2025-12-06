package com.example.booking_service.controller;

import com.example.booking_service.dto.BookingDto;
import com.example.booking_service.entity.Booking;
import com.example.booking_service.mapper.BookingMapper;
import com.example.booking_service.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final BookingMapper mapper;

    public BookingController(BookingService bookingService, BookingMapper mapper) {
        this.bookingService = bookingService;
        this.mapper = mapper;
    }

    // ========================= CREATE =========================
    @PostMapping
    public ResponseEntity<BookingDto> createBooking(@Validated @RequestBody BookingDto dto) {
        Booking entity = mapper.toEntity(dto);
        Booking saved = bookingService.create(entity);
        return ResponseEntity.ok(mapper.toDto(saved));
    }

    // ========================= UPDATE =========================
    @PutMapping("/{id}")
    public ResponseEntity<BookingDto> updateBooking(@Validated @PathVariable Long id,
                                                    @RequestBody BookingDto dto) {
        Booking entity = mapper.toEntity(dto);
        Booking updated = bookingService.update(entity, id);
        return ResponseEntity.ok(mapper.toDto(updated));
    }

    // ========================= GET BY ID =========================
    @GetMapping("/{id}")
    public ResponseEntity<BookingDto> findById(@PathVariable Long id) {
        Booking found = bookingService.getById(id);
        return ResponseEntity.ok(mapper.toDto(found));
    }

    // ========================= DELETE =========================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        bookingService.delete(id);
        log.info("Booking with id {} has been deleted", id);
        return ResponseEntity.noContent().build();
    }
}
