package com.example.booking_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record BookingDto(
    Long id,
    
    @NotBlank(message = "Booking reference is required")
    String bookingReference,
    
    @NotNull(message = "Guest ID is required")
    Long guestId,
    
    @NotBlank(message = "Guest name is required")
    String guestName,
    
    @Email(message = "Invalid email format")
    @NotBlank(message = "Guest email is required")
    String guestEmail,
    
    @NotNull(message = "Room ID is required")
    Long roomId,
    
    @NotBlank(message = "Room number is required")
    String roomNumber,
    
    @NotBlank(message = "Room type is required")
    String roomTypeName,
    
    @NotNull(message = "Check-in date is required")
    LocalDate checkInDate,
    
    @NotNull(message = "Check-out date is required")
    LocalDate checkOutDate,
    
    LocalDateTime actualCheckIn,
    LocalDateTime actualCheckOut,
    
    @NotNull(message = "Number of guests is required")
    @Min(value = 1, message = "At least 1 guest is required")
    @Max(value = 10, message = "Maximum 10 guests allowed")
    Integer numGuests,
    
    @NotBlank(message = "Booking type is required")
    String bookingType,
    
    @NotNull(message = "Total amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Total amount must be greater than 0")
    BigDecimal totalAmount,
    
    BigDecimal discountAmount,
    
    BigDecimal taxAmount,
    
    BigDecimal finalAmount,
    
    String status,
    
    String specialRequests,
    
    String cancellationReason,
    
    Long createdBy,
    
    Long promotionId,
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime createdAt,
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime updatedAt
) {
    // Compact constructor for handling defaults and validation
    public BookingDto {
        // Set default values for BigDecimal fields
        discountAmount = (discountAmount == null) ? BigDecimal.ZERO : discountAmount;
        taxAmount = (taxAmount == null) ? BigDecimal.ZERO : taxAmount;
        
        // Set default status
        if (status == null || status.trim().isEmpty()) {
            status = "PENDING";
        }
        
        // Validate check-in/check-out dates
        if (checkInDate != null && checkOutDate != null && 
            !checkOutDate.isAfter(checkInDate)) {
            throw new IllegalArgumentException("Check-out date must be after check-in date");
        }
        
        // Validate amounts are not negative
        if (discountAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Discount amount cannot be negative");
        }
        
        if (taxAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Tax amount cannot be negative");
        }
        
        if (finalAmount != null && finalAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Final amount cannot be negative");
        }
    }
}