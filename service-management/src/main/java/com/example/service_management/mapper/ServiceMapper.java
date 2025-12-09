package com.example.service_management.mapper;

import com.example.service_management.dto.ServiceRequestDTO;
import com.example.service_management.dto.ServiceResponseDTO;
import com.example.service_management.entity.ServiceManagement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

    // Map from Request DTO → Entity (for create or update)

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ServiceManagement toEntity(ServiceRequestDTO dto);

    // Map from Entity → Response DTO (for API response)
    ServiceResponseDTO toResponseDto(ServiceManagement entity);

    // Map from Entity → Request DTO (ignore database-managed fields)
    ServiceRequestDTO toRequestDto(ServiceManagement entity);
}
