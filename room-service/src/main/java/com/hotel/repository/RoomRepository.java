package com.hotel.repository;

import com.hotel.entity.Room;
import com.hotel.enums.RoomStatus;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    boolean existsByIdAndStatus(Long id, RoomStatus status);
    boolean existsRoomsByRoomNumber(String roomNumber);

    @Query("""
    SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END
    FROM Room r
    WHERE r.id = :roomId
      AND r.status = :status
""")
    Boolean isRoomAvailable(@Param("roomId") Long roomId,
                            @Param("status") RoomStatus status);

}
