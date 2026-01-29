package com.example.booking_service.dto;

import jakarta.persistence.Column;

import java.time.LocalDate;

public class BookingEvent {

    @Column(nullable = false)
    private String bookingReference;

    @Column(nullable = false)
    private Long guestId;

    @Column(nullable = false)
    private LocalDate checkInDate;
}
