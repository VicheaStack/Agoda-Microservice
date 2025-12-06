package com.hotel.room.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MaintenanceDTO(
        Long id,
        Long roomId,
        Long staffId,
        String issueType,
        String description,
        String priority,
        String status,
        LocalDateTime reportedDate,
        LocalDateTime completedDate,
        BigDecimal estimatedCost,
        String roomConditionBefore,
        String roomConditionAfter
) {}
