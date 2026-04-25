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

    @Column(nullable = false)
    private String guestName;

    @Column(nullable = false)
    private String guestEmail;

    @Column(name = "room_id", nullable = false)
    private Long roomId;

    @Column(nullable = false)
    private String roomNumber;

    @Column(nullable = false)
    private String roomType;

    @Column(nullable = false)
    private LocalDate checkInDate;

    @Column(nullable = false)
    private LocalDate checkOutDate;

    private LocalDateTime actualCheckIn;
    private LocalDateTime actualCheckOut;

    @Builder.Default
    private Integer numberOfGuests = 1;

    private String specialRequests;
    private String cancellationReason;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private BookingStatus status = BookingStatus.PENDING;

    @Column(nullable = false)
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

    @Version
    private Long version;

    // Auto-generate booking reference if null
    @PrePersist
    private void generateBookingReference() {
        if (this.bookingReference == null) {
            this.bookingReference = "BOOK-" + System.currentTimeMillis();
        }
    }

    // Calculate final amount
    @PreUpdate
    private void calculateFinalAmount() {
        if (totalAmount != null) {
            this.finalAmount = totalAmount
                    .subtract(discountAmount != null ? discountAmount : BigDecimal.ZERO)
                    .add(taxAmount != null ? taxAmount : BigDecimal.ZERO);
        } else {
            this.totalAmount = BigDecimal.ZERO;
            this.finalAmount = discountAmount.add(taxAmount);
        }
    }

}
