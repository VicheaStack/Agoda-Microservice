package com.example.Loyalty_Service.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "loyalty")
public class Loyalty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "guest_id", nullable = false)
    private Long guestId;

    private Integer points;

    @Column(name = "type", nullable = false)
    private String type;

    private String status;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    private Timestamp transactionDate;

    @PrePersist
    protected void onCreate() {
        if (transactionDate == null) {
            transactionDate = new Timestamp(System.currentTimeMillis());
        }
    }
}
