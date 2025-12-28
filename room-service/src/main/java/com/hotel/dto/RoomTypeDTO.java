package com.hotel.dto;

import com.hotel.enums.RoomCategory;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record RoomTypeDTO(
        Long id,
        RoomCategory roomCategory,
        String description,
        BigDecimal pricePerNight,
        Integer maxOccupancy,
        String bedType,
        String amenities,
        String baseImageUrl,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
