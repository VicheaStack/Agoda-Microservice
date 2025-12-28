package com.hotel.mapper;

import com.hotel.dto.MaintenanceDTO;
import com.hotel.entity.Maintenance;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MaintenanceMapper {

    Maintenance toEntity(MaintenanceDTO dto);

    MaintenanceDTO toDto(Maintenance entity);
}
