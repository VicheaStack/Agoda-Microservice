package com.example.booking_service.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate; // Don't forget this import!

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingCreateRequestDTO {

        @NotNull
        private Long guestId;

        @NotBlank
        private String guestName;

        @Email
        @NotBlank
        private String guestEmail;

        @NotNull
        private Long roomId;

        @NotBlank
        private String roomNumber;

        @NotBlank
        private String roomType;

        // --- ADD THESE THREE FIELDS ---
        @NotNull
        private LocalDate checkInDate;

        @NotNull
        private LocalDate checkOutDate;

        private String status;
        // ------------------------------

        @Builder.Default // Use this so Lombok doesn't overwrite with null
        @Min(1)
        private Integer numberOfGuests = 1;

        private String specialRequests;
        private String cancellationReason;

        @NotNull
        @DecimalMin("0.0")
        private BigDecimal totalAmount;

        @Builder.Default
        private BigDecimal discountAmount = BigDecimal.ZERO;

        @Builder.Default
        private BigDecimal taxAmount = BigDecimal.ZERO;
}