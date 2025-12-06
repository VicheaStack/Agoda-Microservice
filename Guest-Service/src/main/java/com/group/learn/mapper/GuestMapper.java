package com.group.learn.mapper;

import com.group.learn.dto.GuestDTO;
import com.group.learn.dto.GuestResponseDTO;
import com.group.learn.entity.Guest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GuestMapper {

    GuestDTO toDTO(Guest guest);

    @Mapping(source = "createdAt", target = "createdAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "updatedAt", target = "updatedAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "dateOfBirth", target = "dateOfBirth", dateFormat = "yyyy-MM-dd")
    GuestResponseDTO toResponseDto(Guest entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Guest toEntity(GuestDTO guestDTO);
}
