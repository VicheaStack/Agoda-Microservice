package com.hotel.repository;

import com.hotel.entity.Room;
import com.hotel.enums.RoomStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    boolean existsByIdAndStatus(Long id, RoomStatus status);
    boolean existsRoomsByRoomNumber(String roomNumber);

}
