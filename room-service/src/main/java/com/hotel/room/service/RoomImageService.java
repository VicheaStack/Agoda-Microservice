package com.hotel.room.service;

import com.hotel.room.entity.RoomImage;
import org.springframework.stereotype.Service;

@Service
public interface RoomImageService {

    RoomImage saveRoomImage(RoomImage roomImage);
    RoomImage updateRoomImage(RoomImage roomImage, Long id);
    RoomImage getRoomImageById(Long id);
    void deleteRoomImage(Long id);
}
