package com.hotel.entity;

import com.hotel.enums.Bedding;
import com.hotel.enums.RoomCategory;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

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
}
