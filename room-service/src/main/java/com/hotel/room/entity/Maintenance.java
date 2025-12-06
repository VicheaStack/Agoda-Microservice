package com.hotel.room.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "maintenance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_id", nullable = false)
    private Long roomId; // ID-only linking

    @Column(name = "staff_id")
    private Long staffId;

    @Column(name = "issue_type", length = 100)
    private String issueType;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "priority", length = 20)
    @Builder.Default
    private String priority = "Low";

    @Column(name = "status", length = 30)
    @Builder.Default
    private String status = "Reported";

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "reported_date", updatable = false)
    private LocalDateTime reportedDate;

    @Column(name = "completed_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime completedDate;

    @Column(name = "estimated_cost", precision = 10, scale = 2)
    private BigDecimal estimatedCost;

    @Column(name = "room_condition_before", columnDefinition = "TEXT")
    private String roomConditionBefore;

    @Column(name = "room_condition_after", columnDefinition = "TEXT")
    private String roomConditionAfter;
}
