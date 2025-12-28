package com.example.booking_service.dto;

import com.example.booking_service.Enum.BookingStatus;
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
        String roomType, // Changed from roomTypeName to match entity

        @NotNull(message = "Check-in date is required")
        @FutureOrPresent(message = "Check-in date must be today or in the future")
        LocalDate checkInDate,

        @NotNull(message = "Check-out date is required")
        @Future(message = "Check-out date must be in the future")
        LocalDate checkOutDate,

        LocalDateTime actualCheckIn,
        LocalDateTime actualCheckOut,

        @NotNull(message = "Number of guests is required")
        @Min(value = 1, message = "At least 1 guest is required")
        @Max(value = 10, message = "Maximum 10 guests allowed")
        Integer numberOfGuests, // Changed from numGuests to match entity

        @NotNull(message = "Total amount is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "Total amount must be greater than 0")
        BigDecimal totalAmount,

        @DecimalMin(value = "0.0", message = "Discount amount cannot be negative")
        BigDecimal discountAmount,

        @DecimalMin(value = "0.0", message = "Tax amount cannot be negative")
        BigDecimal taxAmount,

        BigDecimal finalAmount,

        BookingStatus status, // Changed from String to BookingStatus enum

        String specialRequests,

        String cancellationReason,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime createdAt,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime updatedAt
) {
    // Constructor with validation for check-out date
    public BookingDto {
        if (checkInDate != null && checkOutDate != null) {
            if (!checkOutDate.isAfter(checkInDate)) {
                throw new IllegalArgumentException("Check-out date must be after check-in date");
            }
        }

        // Set defaults
        if (discountAmount == null) {
            discountAmount = BigDecimal.ZERO;
        }
        if (taxAmount == null) {
            taxAmount = BigDecimal.ZERO;
        }
        if (status == null) {
            status = BookingStatus.PENDING;
        }
    }
}