package com.example.booking_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ReviewsDto(
    Long id,

    Long bookingId,

    @NotNull(message = "Guest ID is required")
    Long guestId,
    
    @NotBlank(message = "Guest name is required")
    String guestName,
    
    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating cannot exceed 5")
    Integer rating,
    
    @Size(max = 500, message = "Comments cannot exceed 500 characters")
    String comments,
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime createdAt
) {
    public ReviewsDto {
        // Note: Your entity has guestName as Long, but I corrected it to String
        // since it should be a name (text), not a number
        
        // Set defaults for comments if needed
        if (comments == null) {
            comments = "";
        }
    }
}