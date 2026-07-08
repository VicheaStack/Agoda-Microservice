package com.example.booking_service.controller;

import com.example.booking_service.dto.RoomDTO;
import com.example.booking_service.serviceImpl.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomDTO> create(@RequestBody RoomDTO roomDTO) {
        return ResponseEntity.ok(roomService.create(roomDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomDTO> update(
            @RequestBody RoomDTO roomDTO,
            @PathVariable Long id) {
        return ResponseEntity.ok(roomService.update(roomDTO, id));
    }

    @GetMapping
    public ResponseEntity<Page<RoomDTO>> checkAvailability(Pageable pageable) {
        return ResponseEntity.ok(roomService.checkAvailability(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(roomService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roomService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{roomId}/available")
    public ResponseEntity<Boolean> isRoomAvailable(@PathVariable Long roomId) {
        return ResponseEntity.ok(roomService.isRoomAvailable(roomId));
    }
}