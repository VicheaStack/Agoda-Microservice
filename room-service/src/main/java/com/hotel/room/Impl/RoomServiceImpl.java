package com.hotel.room.Impl;

import com.hotel.room.config.ResourceNotFoundException;
import com.hotel.room.entity.Room;
import com.hotel.room.repository.RoomRepository;
import com.hotel.room.service.RoomService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    private static final Integer ROOM_AVAILABILITY = 250;
    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room create(Room room) {
        if(room == null){
            throw new IllegalArgumentException("Room cannot be null");
        }

        long existingRooms = roomRepository.count();

        if(existingRooms >= ROOM_AVAILABILITY){
            throw new IllegalStateException("Cannot add more rooms. Maximum capacity reached.");
        }


        return roomRepository.save(room);
    }

    @Override
    public Room update(Room room, Long id) {
        Room check = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id: " + id));

        check.setRoomNumber(room.getRoomNumber());
        check.setRoomTypeId(room.getRoomTypeId());
        check.setPrice(room.getPrice());
        check.setStatus(room.getStatus());

        return roomRepository.save(check);
    }

    @Override
    public Page<Room> checkAvailability(Pageable pageable) {
        // Filter available rooms from the database
        List<Room> availableRooms = roomRepository.findAll()
                .stream()
                .filter(room -> "AVAILABLE".equalsIgnoreCase(String.valueOf(room.getStatus())))
                .collect(Collectors.toList());

        // Apply pagination
        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();
        int start = pageNumber * pageSize;
        int end = Math.min(start + pageSize, availableRooms.size());

        if (start > availableRooms.size()) {
            return Page.empty(pageable);
        }

        List<Room> pageContent = availableRooms.subList(start, end);

        return new PageImpl<>(
                pageContent,
                pageable,
                availableRooms.size()
        );
    }

    @Override
    public Room getById(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("room can't found"));
        return room;
    }

    @Override
    public void delete(Long id) {
        if(!roomRepository.existsById(id)){
            new ResourceNotFoundException("can't found room avaliable");
        }
    }
}
