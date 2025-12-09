package com.example.service_management.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Entity
@Table(name = "booking_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bookingId;

    @Column(length = 50)
    private String bookingReference;

    // FK to services table
    private Long serviceId;

    @Column(length = 100)
    private String serviceName;

    private Long staffId;

    @Column(length = 100)
    private String staffName;

    @Column(nullable = false)
    private Integer quantity = 1;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(precision = 10, scale = 2)
    private BigDecimal totalPrice;

    private LocalDate serviceDate = LocalDate.now();

    @Column(columnDefinition = "TEXT")
    private String notes;

    private LocalDateTime createdAt = LocalDateTime.now();

//    @PrePersist
//    public void prePersist() {
//        if (totalPrice == null) {
//            this.totalPrice = this.unitPrice * this.quantity;
//        }
//    }
}
