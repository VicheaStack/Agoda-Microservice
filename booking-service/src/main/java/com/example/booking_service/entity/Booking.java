package com.example.booking_service.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "booking")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "booking_reference", nullable = false, unique = true, length = 50)
    private String bookingReference;
    
    @Column(name = "guest_id", nullable = false)
    private Long guestId;
    
    @Column(name = "guest_name", length = 100)
    private String guestName;
    
    @Column(name = "guest_email", length = 100)
    private String guestEmail;
    
    @Column(name = "room_id", nullable = false)
    private Long roomId;
    
    @Column(name = "room_number", length = 10)
    private String roomNumber;
    
    @Column(name = "room_type_name", length = 30)
    private String roomTypeName;
    
    @Column(name = "check_in_date", nullable = false)
    private LocalDate checkInDate;
    
    @Column(name = "check_out_date", nullable = false)
    private LocalDate checkOutDate;
    
    @Column(name = "actual_check_in")
    private LocalDateTime actualCheckIn;
    
    @Column(name = "actual_check_out")
    private LocalDateTime actualCheckOut;
    
    @Column(name = "num_guests")
    private Integer numGuests;
    
    @Column(name = "booking_type", length = 30)
    private String bookingType;
    
    @Column(name = "total_amount", precision = 10, scale = 2)
    private BigDecimal totalAmount;
    
    @Column(name = "discount_amount", precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal discountAmount = BigDecimal.ZERO;
    
    @Column(name = "tax_amount", precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal taxAmount = BigDecimal.ZERO;
    
    @Column(name = "final_amount", precision = 10, scale = 2)
    private BigDecimal finalAmount;
    
    @Column(name = "status", length = 30)
    @Builder.Default
    private String status = "Pending";
    
    @Column(name = "special_requests", columnDefinition = "TEXT")
    private String specialRequests;
    
    @Column(name = "cancellation_reason", columnDefinition = "TEXT")
    private String cancellationReason;
    
    @Column(name = "created_by")
    private Long createdBy;
    
    @Column(name = "promotion_id")
    private Long promotionId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}