package com.hotel.audit.mapper;


import com.hotel.audit.entity.AuditLog;
import com.hotel.audit.entity.dto.AuditLogRequestDTO;
import com.hotel.audit.entity.dto.AuditLogResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class AuditLogMapper {

    public AuditLog toEntity(AuditLogRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        AuditLog entity = new AuditLog();
        entity.setServiceName(dto.serviceName());
        entity.setUserId(dto.userId());
        entity.setUserType(dto.userType());
        entity.setAction(dto.action());
        entity.setEntityName(dto.entityName());
        entity.setEntityId(dto.entityId());
        entity.setDescription(dto.description());

        return entity;
    }

    public AuditLogResponseDTO toResponseDTO(AuditLog entity) {
        if (entity == null) {
            return null;
        }

        return new AuditLogResponseDTO(
                entity.getId(),
                entity.getServiceName(),
                entity.getUserId(),
                entity.getUserType(),
                entity.getAction(),
                entity.getEntityName(),
                entity.getEntityId(),
                entity.getDescription(),
                entity.getCreatedAt()
        );
    }
}
