package com.example.staff_service.dto;

import com.example.staff_service.Enum.StaffStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public record StaffDTO(
        Long id,
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        String citizenship,
        String email,
        String phone,
        String username,
        String password,  // Note: In practice, you might want separate DTOs for create/update
        String role,
        String department,
        BigDecimal salary,
        LocalDate hireDate,
        StaffStatus status
) {

}