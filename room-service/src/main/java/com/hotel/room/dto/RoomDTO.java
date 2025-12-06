package com.hotel.room.dto;

import java.time.LocalDateTime;

public record RoomDTO(
        Long id,
        String roomNumber,
        Long roomTypeId,
        Integer floor,
        String status,
        Double price,
        Boolean breakfastIncluded,
        Boolean isSmoking,
        String features,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
