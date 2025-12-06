package com.hotel.room.repository;

import com.hotel.room.entity.RoomType;
import com.hotel.room.enums.Bedding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType , Long> {
    int countByBedType(Bedding bedType);
}
