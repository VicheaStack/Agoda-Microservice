package com.hotel.room.repository;

import com.hotel.room.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    boolean existsRoomsByRoomNumber(String roomNumber);

}
