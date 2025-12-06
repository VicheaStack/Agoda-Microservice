package com.hotel.room.mapper;

import com.hotel.room.dto.RoomDTO;
import com.hotel.room.entity.Room;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    Room toEntity(RoomDTO dto);
    RoomDTO toDto(Room entity);

}
