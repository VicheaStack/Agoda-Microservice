package com.example.booking_service.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

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

        @Min(1)
        private Integer numberOfGuests = 1;

        private String specialRequests;
        private String cancellationReason;

        @NotNull
        @DecimalMin("0.0")
        private BigDecimal totalAmount;

        private BigDecimal discountAmount = BigDecimal.ZERO;
        private BigDecimal taxAmount = BigDecimal.ZERO;
}
