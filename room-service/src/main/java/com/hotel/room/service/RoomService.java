package com.hotel.room.service;

import com.hotel.room.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface RoomService {

    Room create(Room room);
    Room update(Room room, Long id);
    Page<Room> checkAvailability(Pageable pageable);
    Room getById(Long id);
    void delete(Long id);
}
