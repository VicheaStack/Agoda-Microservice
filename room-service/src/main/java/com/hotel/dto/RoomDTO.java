package com.hotel.dto;

import com.hotel.enums.RoomStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record RoomDTO(
        Long id,
        String roomNumber,
        Long roomTypeId,
        String roomTypeName,
        Integer floor,
        RoomStatus status,
        BigDecimal pricePerNight,
        Boolean breakfastIncluded,
        Boolean smokingAllowed,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
