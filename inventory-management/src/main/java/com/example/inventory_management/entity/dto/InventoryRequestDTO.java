package com.example.inventory_management.entity.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record InventoryRequestDTO(
        String itemName,
        String category,
        Integer quantity,
        Integer reorderLevel,
        BigDecimal unitPrice,
        String status
) {
}
