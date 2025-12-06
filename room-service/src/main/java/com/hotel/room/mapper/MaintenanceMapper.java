package com.hotel.room.mapper;

import com.hotel.room.dto.MaintenanceDTO;
import com.hotel.room.entity.Maintenance;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MaintenanceMapper {

    Maintenance toEntity(MaintenanceDTO dto);

    MaintenanceDTO toDto(Maintenance entity);
}
