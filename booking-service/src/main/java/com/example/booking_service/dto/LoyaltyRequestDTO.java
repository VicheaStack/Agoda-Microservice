package com.example.booking_service.dto;

import java.math.BigDecimal;

public record LoyaltyRequestDTO(
        Long guestId,
        BigDecimal amount,
        String type
) {
}
