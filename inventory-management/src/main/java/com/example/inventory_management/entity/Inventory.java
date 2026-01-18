package com.example.inventory_management.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_name", length = 100, nullable = false)
    private String itemName;

    @Column(name = "category", length = 50, nullable = false)
    private String category;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "reorder_level", nullable = false)
    private Integer reorderLevel;

    @Column(name = "unit_price", precision = 10, scale = 2, nullable = false)
    private BigDecimal unitPrice;

    @Column(length = 20, nullable = false)
    private String status;

    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;

    @PrePersist
    @PreUpdate
    protected void onUpdate() {
        this.lastUpdated = LocalDateTime.now();
        if (this.status == null) {
            this.status = "Available";
        }
    }
}
