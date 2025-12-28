package com.hotel.dto;

import java.math.BigDecimal;

public record RoomRequestDTO(String roomNumber,
                             Long roomTypeId,
                             String roomTypeName,
                             Integer floor,
                             String status,           // Or use RoomStatus enum if preferred
                             BigDecimal pricePerNight,
                             Boolean breakfastIncluded,
                             Boolean smokingAllowed,
                             String description) {
}
