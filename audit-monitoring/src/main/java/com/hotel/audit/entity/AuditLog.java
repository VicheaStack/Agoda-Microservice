package com.hotel.audit.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditLog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "service_name", length = 50, nullable = false)
    private String serviceName;
    
    @Column(name = "user_id")
    private Long userId;
    
    @Column(name = "user_type", length = 20)
    private String userType;
    
    @Column(name = "action", length = 100, nullable = false)
    private String action;
    
    @Column(name = "entity_name", length = 50, nullable = false)
    private String entityName;
    
    @Column(name = "entity_id")
    private Long entityId;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}