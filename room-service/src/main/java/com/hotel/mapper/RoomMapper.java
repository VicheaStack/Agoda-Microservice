package com.hotel.mapper;

import com.hotel.dto.RoomDTO;
import com.hotel.entity.Room;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    Room toEntity(RoomDTO dto);
    RoomDTO toDto(Room entity);

}
