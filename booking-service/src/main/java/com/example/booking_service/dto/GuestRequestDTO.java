package com.example.booking_service.dto;

public record GuestRequestDTO(
        Long id,
        String firstName,
        String lastName,
        String phone,
        String email) {
}
