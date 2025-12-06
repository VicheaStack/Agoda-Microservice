package com.group.learn.dto;

import java.time.LocalDateTime;

// REMOVE the BodyInserter implementation - it's breaking WebClient!
public record LoyaltyDTO(
        Long id,
        Long guestId,
        Integer points,
        String type,
        String status,
        String description,
        LocalDateTime transactionDate
) {
    // Remove everything else - just a plain record!
}