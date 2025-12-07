package com.example.staff_service.mapper;

import com.example.staff_service.dto.StaffDTO;
import com.example.staff_service.entity.Staff;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StaffMappper {

    @Mapping(target = "id", ignore = true)           // 🔴 ADD THESE
    @Mapping(target = "createdAt", ignore = true)    // 🔴
    @Mapping(target = "updatedAt", ignore = true)    // 🔴
    Staff toEntity(StaffDTO dto);

    StaffDTO toDTO(Staff entity);
}