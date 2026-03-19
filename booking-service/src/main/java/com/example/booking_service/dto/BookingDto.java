package com.example.booking_service.dto;

import com.example.booking_service.Enum.BookingStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingDto {

    private Long id;
    private String bookingReference;
    private Long guestId;
    private String guestName;
    private String guestEmail;
    private Long roomId;
    private String roomNumber;
    private String roomType;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private LocalDateTime actualCheckIn;
    private LocalDateTime actualCheckOut;
    private Integer numberOfGuests;
    private String specialRequests;
    private String cancellationReason;
    private BookingStatus status;
    private BigDecimal totalAmount;
    private BigDecimal discountAmount;
    private BigDecimal taxAmount;
    private BigDecimal finalAmount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
