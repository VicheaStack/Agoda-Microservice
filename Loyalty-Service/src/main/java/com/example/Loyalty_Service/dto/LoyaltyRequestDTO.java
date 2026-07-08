package com.example.Loyalty_Service.dto;

import jakarta.validation.constraints.NotNull;

public record LoyaltyRequestDTO(
        @NotNull(message = "Guest ID is required")
        Long guestId,
        Integer points,
        @NotNull(message = "Loyalty type is required")
        String type,
        String status,
        String description,
        String transactionDate
) {}