package com.hotel.room.Impl;

import com.hotel.room.config.ResourceNotFoundException;
import com.hotel.room.dto.RoomTypeDTO;
import com.hotel.room.entity.Room;
import com.hotel.room.entity.RoomType;
import com.hotel.room.enums.Bedding;
import com.hotel.room.repository.RoomTypeRepository;
import com.hotel.room.service.RoomService;
import com.hotel.room.service.RoomTypeService;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class RoomTypeServiceImpl implements RoomTypeService {

    private final RoomTypeRepository roomTypeRepository;

    public RoomTypeServiceImpl(RoomTypeRepository roomTypeRepository){
        this.roomTypeRepository = roomTypeRepository;
    }

    @Override
    public RoomType createRoomType(RoomType roomType) {
        Bedding bedding = roomType.getBedType();

        if(bedding == null){
            throw new IllegalArgumentException("bedType can't be empty");
        }

        switch (bedding) {
            case KING -> {
                if (roomTypeRepository.countByBedType(Bedding.KING) >= 80){
                    throw new IllegalStateException("King bed room are full");
                }
            }

            case QUEEN -> {
                if (roomTypeRepository.countByBedType(Bedding.DOUBLE) >= 70){
                    throw new IllegalStateException("Queen bed room are full");
                }
            }

            case DOUBLE -> {
                if (roomTypeRepository.countByBedType(Bedding.DOUBLE) >= 50){
                    throw new IllegalStateException("Double bed room are full");
                }
            }

            case SINGLE -> {
                if (roomTypeRepository.countByBedType(Bedding.SINGLE) >= 50){
                    throw new IllegalStateException("Single bed room are full");
                }
            }

        }

        return roomTypeRepository.save(roomType);
    }

    @Override
    public RoomType updateRoomType(Long id, RoomType roomType) {
        // 1. Find the existing room type
        RoomType existingRoom = roomTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room type with id " + id + " not found"));

        // 2. Update fields from the provided roomType object
        // Check each field and update if it's not null
        if(existingRoom.getRoomCategory() == null){
            existingRoom.setRoomCategory(roomType.getRoomCategory());
        }

        if (roomType.getDescription() != null) {
            existingRoom.setDescription(roomType.getDescription());
        }

        if (roomType.getBedType() != null) {
            // Check if it's the same bedding (your original logic)
            if (existingRoom.getBedType() == roomType.getBedType()) {
                throw new IllegalStateException("Room already has " + roomType.getBedType() + " bedding");
            }
            existingRoom.setBedType(roomType.getBedType());
        }

        if (roomType.getPricePerNight() != null) {
            existingRoom.setPricePerNight(roomType.getPricePerNight());
        }

        if (roomType.getRoomCategory() != null) {
            existingRoom.setRoomCategory(roomType.getRoomCategory());
        }

        // 3. Save and return
        return roomTypeRepository.save(existingRoom);
    }

    @Override
    public RoomType getRoomTypeById(Long id) {
        RoomType roomType = roomTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("can't find room avaliable"));

        return roomType;
    }

    @Override
    public void deleteRoomType(Long id) {
        if(!roomTypeRepository.existsById(id)){
            roomTypeRepository.deleteById(id);
        }
    }
}
