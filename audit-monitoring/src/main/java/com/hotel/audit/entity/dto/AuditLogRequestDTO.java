package com.hotel.audit.entity.dto;

import jakarta.validation.constraints.NotBlank;

public record AuditLogRequestDTO(

        @NotBlank
        String serviceName,

        Long userId,

        String userType,

        @NotBlank
        String action,

        @NotBlank
        String entityName,

        Long entityId,

        String description
) {
}
