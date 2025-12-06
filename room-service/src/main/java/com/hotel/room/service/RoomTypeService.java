package com.hotel.room.service;

import com.hotel.room.entity.Room;
import com.hotel.room.entity.RoomType;
import com.hotel.room.enums.Bedding;
import org.springframework.stereotype.Service;

@Service
public interface RoomTypeService {

    RoomType createRoomType(RoomType roomType);
    RoomType updateRoomType(Long id, RoomType roomType);
    RoomType getRoomTypeById(Long id);
    void deleteRoomType(Long id);


}
