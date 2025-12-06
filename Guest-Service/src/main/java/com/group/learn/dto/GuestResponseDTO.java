package com.group.learn.dto;

import java.util.List;

public record GuestResponseDTO(
        Long id,
        String firstName,
        String lastName,
        String dateOfBirth,
        String phone,
        String email,
        String passportNumber,
        String nationality,
        String gender,
        String address,
        String createdAt,
        String updatedAt//  <-- FIX HERE
) {}
