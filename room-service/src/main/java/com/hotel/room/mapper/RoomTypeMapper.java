package com.hotel.room.mapper;

import com.hotel.room.dto.RoomTypeDTO;
import com.hotel.room.entity.RoomType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoomTypeMapper {

    RoomType toEntity(RoomTypeDTO dto);

    RoomTypeDTO toDto(RoomType entity);
}
