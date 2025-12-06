package com.hotel.room.controller;

import com.hotel.room.entity.RoomImage;
import com.hotel.room.service.RoomImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/room-images")
public class RoomImageController {

    private final RoomImageService roomImageService;

    public RoomImageController(RoomImageService roomImageService) {
        this.roomImageService = roomImageService;
    }

    @PostMapping
    public ResponseEntity<RoomImage> createRoomImage(@RequestBody RoomImage roomImage) {

        RoomImage saved = roomImageService.saveRoomImage(roomImage);

        return ResponseEntity
                .created(URI.create("/api/room-images/" + saved.getId()))
                .body(saved);
    }

    // --------------------------
    //          UPDATE IMAGE
    // --------------------------
    @PutMapping("/{id}")
    public ResponseEntity<RoomImage> updateRoomImage(
            @PathVariable Long id,
            @RequestBody RoomImage roomImage
    ) {
        RoomImage updated = roomImageService.updateRoomImage(roomImage, id);
        return ResponseEntity.ok(updated);
    }

    // --------------------------
    //         GET IMAGE BY ID
    // --------------------------
    @GetMapping("/{id}")
    public ResponseEntity<RoomImage> getRoomImageById(@PathVariable Long id) {
        RoomImage roomImage = roomImageService.getRoomImageById(id);
        return ResponseEntity.ok(roomImage);
    }

    // --------------------------
    //          DELETE IMAGE
    // --------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoomImage(@PathVariable Long id) {
        roomImageService.deleteRoomImage(id);
        return ResponseEntity.noContent().build();
    }
}
