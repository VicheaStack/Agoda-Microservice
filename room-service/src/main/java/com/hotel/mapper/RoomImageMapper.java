package com.hotel.mapper;

import com.hotel.dto.RoomImageDTO;
import com.hotel.entity.RoomImage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomImageMapper {

    RoomImage toEntity(RoomImageDTO dto);

    RoomImageDTO toDto(RoomImage entity);

}
