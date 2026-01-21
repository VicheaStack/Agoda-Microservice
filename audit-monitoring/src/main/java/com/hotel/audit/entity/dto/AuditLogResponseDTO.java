package com.hotel.audit.entity.dto;

import java.time.LocalDateTime;

public record AuditLogResponseDTO(
        Long id,
        String serviceName,
        Long userId,
        String userType,
        String action,
        String entityName,
        Long entityId,
        String description,
        LocalDateTime createdAt
) {
}
