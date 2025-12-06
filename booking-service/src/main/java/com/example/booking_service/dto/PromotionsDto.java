package com.example.booking_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PromotionsDto(
    Long id,

    Long bookId,
    
    String code,
    
    String description,
    
    @DecimalMin(value = "0.0", message = "Discount percentage cannot be negative")
    @DecimalMax(value = "100.0", message = "Discount percentage cannot exceed 100")
    Double discountPercentage,
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "Start date is required")
    LocalDateTime startDate,
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "End date is required")
    LocalDateTime endDate,
    
    Boolean active
) {
    public PromotionsDto {
        // Set default active status
        if (active == null) {
            active = true;
        }
        
        // Validate date logic
        if (startDate != null && endDate != null && !endDate.isAfter(startDate)) {
            throw new IllegalArgumentException("End date must be after start date");
        }
        
        // Handle null discount percentage
        if (discountPercentage == null) {
            discountPercentage = 0.0;
        }
    }
}