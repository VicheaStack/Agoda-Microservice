package com.example.booking_service.dto;

import java.math.BigDecimal;

public record RoomAvailabilityResponse(Boolean available,
                                       String roomType,
                                       String roomNumber,
                                       BigDecimal pricePerNight) {
}
