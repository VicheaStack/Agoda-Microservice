// booking-service/src/main/java/com/hotel/booking/entity/Booking.java
package com.example.booking_service.entity;

import com.example.booking_service.Enum.BookingStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String bookingReference;

    @Column(name = "guest_id", nullable = false)
    private Long guestId;

    private String guestName;
    private String guestEmail;

    @Column(name = "room_id", nullable = false)
    private Long roomId;
    private String roomNumber;
    private String roomType;

    @Column(nullable = false)
    private LocalDate checkInDate;

    @Column(nullable = false)
    private LocalDate checkOutDate;

    private LocalDateTime actualCheckIn;
    private LocalDateTime actualCheckOut;

    private Integer numberOfGuests;

    private String specialRequests;
    private String cancellationReason;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private BookingStatus status = BookingStatus.PENDING;

    private BigDecimal totalAmount;

    @Builder.Default
    private BigDecimal discountAmount = BigDecimal.ZERO;

    @Builder.Default
    private BigDecimal taxAmount = BigDecimal.ZERO;

    private BigDecimal finalAmount;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // Helper method to calculate final amount
    @PrePersist
    @PreUpdate
    private void calculateFinalAmount() {
        if (totalAmount != null) {
            this.finalAmount = totalAmount
                    .subtract(discountAmount != null ? discountAmount : BigDecimal.ZERO)
                    .add(taxAmount != null ? taxAmount : BigDecimal.ZERO);
        }
    }
}