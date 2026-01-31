package com.hotel.mapper;

import com.hotel.dto.RoomDTO;
import com.hotel.dto.RoomRequestDTO;
import com.hotel.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    @Mapping(source = "pricePerNight", target = "pricePerNight")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Room toEntity(RoomRequestDTO dto);

    @Mapping(source = "pricePerNight", target = "pricePerNight")
    RoomDTO toDto(Room entity);

}
