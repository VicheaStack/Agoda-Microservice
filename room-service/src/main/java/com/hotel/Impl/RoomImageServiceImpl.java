package com.hotel.Impl;

import com.hotel.config.ResourceNotFoundException;
import com.hotel.entity.RoomImage;
import com.hotel.repository.RoomImageRepository;
import com.hotel.service.RoomImageService;
import org.springframework.stereotype.Service;

@Service
public class RoomImageServiceImpl implements RoomImageService {

    private final RoomImageRepository roomImageRepository;

    public RoomImageServiceImpl(RoomImageRepository roomImageRepository) {
        this.roomImageRepository = roomImageRepository;
    }

    @Override
    public RoomImage saveRoomImage(RoomImage roomImage) {

        if (roomImage == null) {
            throw new IllegalArgumentException("RoomImage cannot be null");
        }

        if (roomImage.getImageUrl() == null || roomImage.getImageUrl().isEmpty()) {
            throw new IllegalArgumentException("Image URL cannot be empty");
        }

        if (roomImage.getRoomId() == null) {
            throw new IllegalArgumentException("Room ID is required for image");
        }

        return roomImageRepository.save(roomImage);
    }

    // --------------------------
    //       UPDATE IMAGE
    // --------------------------
    @Override
    public RoomImage updateRoomImage(RoomImage updated, Long id) {

        RoomImage existing = roomImageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RoomImage not found with id: " + id));

        if (updated.getImageUrl() != null) {
            existing.setImageUrl(updated.getImageUrl());
        }

        if (updated.getRoomId() != null) {
            existing.setRoomId(updated.getRoomId());
        }

        return roomImageRepository.save(existing);
    }

    // --------------------------
    //       GET BY ID
    // --------------------------
    @Override
    public RoomImage getRoomImageById(Long id) {

        return roomImageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RoomImage not found with id: " + id));
    }

    // --------------------------
    //       DELETE IMAGE
    // --------------------------
    @Override
    public void deleteRoomImage(Long id) {

        if (!roomImageRepository.existsById(id)) {
            throw new ResourceNotFoundException("RoomImage not found with id: " + id);
        }

        roomImageRepository.deleteById(id);
    }
}
