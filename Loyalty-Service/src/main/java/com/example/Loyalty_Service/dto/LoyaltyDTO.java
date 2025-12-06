package com.example.Loyalty_Service.dto;

public record LoyaltyDTO(Long id,
                      Long guestId,
                      Integer points,
                      String type,
                      String status,
                      String description,
                      String transactionDate) {
}
