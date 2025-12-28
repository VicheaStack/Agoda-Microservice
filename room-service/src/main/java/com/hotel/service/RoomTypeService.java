package com.hotel.service;

import com.hotel.entity.RoomType;
import org.springframework.stereotype.Service;

@Service
public interface RoomTypeService {

    RoomType createRoomType(RoomType roomType);
    RoomType updateRoomType(Long id, RoomType roomType);
    RoomType getRoomTypeById(Long id);
    void deleteRoomType(Long id);


}
