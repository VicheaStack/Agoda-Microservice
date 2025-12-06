package com.hotel.room.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotel.room.enums.Bedding;
import com.hotel.room.enums.RoomCategory;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "room_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 30, nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private RoomCategory roomCategory;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "price_per_night", precision = 10, scale = 2, nullable = false)
    private BigDecimal pricePerNight;

    @Column(name = "max_occupancy", nullable = false)
    private Integer maxOccupancy;

    @Column(name = "bed_type", length = 30)
    @Enumerated(EnumType.STRING)
    private Bedding bedType;


    @Column(name = "amenities", columnDefinition = "JSON")
    private String amenities; // store as JSON string

    @Column(name = "base_image_url", length = 255)
    private String baseImageUrl;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
