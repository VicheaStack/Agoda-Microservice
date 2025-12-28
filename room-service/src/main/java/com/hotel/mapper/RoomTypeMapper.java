package com.hotel.mapper;

import com.hotel.dto.RoomTypeDTO;
import com.hotel.entity.RoomType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomTypeMapper {

    RoomType toEntity(RoomTypeDTO dto);

    RoomTypeDTO toDto(RoomType entity);
}
