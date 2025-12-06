package com.hotel.room.mapper;

import com.hotel.room.dto.RoomImageDTO;
import com.hotel.room.entity.RoomImage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomImageMapper {

    RoomImage toEntity(RoomImageDTO dto);

    RoomImageDTO toDto(RoomImage entity);

}
