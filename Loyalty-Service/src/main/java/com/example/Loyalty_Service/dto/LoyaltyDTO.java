package com.example.Loyalty_Service.dto;

import java.time.LocalDateTime;

public record LoyaltyDTO(
        Long id,
        Long guestId,
        Integer points,
        String type,
        String status,
        String description,
        LocalDateTime transactionDate
) {}