package com.example.booking_service.controller;

import com.example.booking_service.dto.BookingCreateRequestDTO;
import com.example.booking_service.dto.BookingDto;
import com.example.booking_service.entity.Booking;
import com.example.booking_service.mapper.BookingMapper;
import com.example.booking_service.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final BookingMapper mapper;

    public BookingController(BookingService bookingService,
                             BookingMapper mapper) {
        this.bookingService = bookingService;
        this.mapper = mapper;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<BookingDto> createBooking(@Validated @RequestBody BookingCreateRequestDTO dto) {
        Booking entity = mapper.toEntity(dto);
        Booking booking = bookingService.create(entity);
        BookingDto save = mapper.toDto(booking);
        return ResponseEntity.ok(save);
    }

//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping
//    public ResponseEntity<Booking> create(@RequestBody Booking booking) {
//        return bookingService.create(booking)
//                .map(saved -> ResponseEntity.status(HttpStatus.CREATED).body(saved))
//                .onErrorResume(RuntimeException.class,
//                        ex -> Mono.just(ResponseEntity.badRequest().build()));
//    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingDto> updateBooking(@Validated @PathVariable Long id,
                                                    @RequestBody BookingCreateRequestDTO dto) {
        Booking entity = mapper.toEntity(dto);
        Booking updated = bookingService.update(entity, id);
        return ResponseEntity.ok(mapper.toDto(updated));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDto> findById(@PathVariable Long id) {
        Booking found = bookingService.getById(id);
        return ResponseEntity.ok(mapper.toDto(found));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        bookingService.delete(id);
        log.info("Booking with id {} has been deleted", id);
        return ResponseEntity.noContent().build();
    }
}
