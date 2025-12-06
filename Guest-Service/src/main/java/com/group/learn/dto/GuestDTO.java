package com.group.learn.dto;

public record GuestDTO(
        String firstName,
        String lastName,
        String dateOfBirth,
        String phone,
        String email,
        String passportNumber,
        String nationality,
        String gender,
        String address) {
}
