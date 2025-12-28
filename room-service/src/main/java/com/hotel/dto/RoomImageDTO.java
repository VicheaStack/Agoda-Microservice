package com.hotel.dto;

import java.time.LocalDateTime;

public record RoomImageDTO(
        Long id,
        Long roomId,
        String imageUrl,
        String description,
        LocalDateTime createdAt
) {}
