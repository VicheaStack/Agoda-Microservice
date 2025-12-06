package com.hotel.room.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotel.room.enums.RoomStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "rooms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_number", length = 10, nullable = false, unique = true)
    private String roomNumber;

    @Column(name = "room_type_id", nullable = false)
    private Long roomTypeId; // ID-only linking

    @Column(name = "floor")
    private Integer floor;

    @Column(name = "Available")
    @Enumerated(EnumType.STRING)
    private RoomStatus status;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "breakfast_included")
    @Builder.Default
    private Boolean breakfastIncluded = false;

    @Column(name = "is_smoking")
    @Builder.Default
    private Boolean isSmoking = false;

    @Column(name = "features", columnDefinition = "JSON")
    private String features;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
