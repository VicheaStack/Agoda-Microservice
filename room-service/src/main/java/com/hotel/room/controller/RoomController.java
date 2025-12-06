package com.hotel.room.controller;

import com.hotel.room.dto.RoomDTO;
import com.hotel.room.entity.Room;
import com.hotel.room.mapper.RoomMapper;
import com.hotel.room.service.RoomService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/room")
public class RoomController {

    private final RoomService roomService;
    private final RoomMapper mapper;

    public RoomController(RoomService roomService, RoomMapper mapper) {
        this.roomService = roomService;
        this.mapper = mapper;
    }

    // ----------------------------------------
    //               CREATE ROOM
    // ----------------------------------------
    @PostMapping
    public ResponseEntity<?> create(@RequestBody RoomDTO dto) {

        Room room = mapper.toEntity(dto);
        Room saved = roomService.create(room);

        return ResponseEntity
                .ok(mapper.toDto(saved));
    }

    // ----------------------------------------
    //               UPDATE ROOM
    // ----------------------------------------
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody RoomDTO dto
    ) {
        Room updated = roomService.update(mapper.toEntity(dto), id);
        return ResponseEntity.ok(mapper.toDto(updated));
    }

    // ----------------------------------------
    //               GET ROOM BY ID
    // ----------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Room room = roomService.getById(id);
        return ResponseEntity.ok(mapper.toDto(room));
    }

    // ----------------------------------------
    //               DELETE ROOM
    // ----------------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        roomService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ----------------------------------------
    //        CHECK ROOM AVAILABILITY (PAGE)
    // ----------------------------------------
    @GetMapping("/availability")
    public ResponseEntity<Page<RoomDTO>> checkAvailability(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Room> availableRooms = roomService.checkAvailability(PageRequest.of(page, size));

        Page<RoomDTO> dtoPage = availableRooms.map(mapper::toDto);

        return ResponseEntity.ok(dtoPage);
    }
}
