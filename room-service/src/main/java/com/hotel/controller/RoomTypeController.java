package com.hotel.controller;

import com.hotel.dto.RoomTypeDTO;
import com.hotel.entity.RoomType;
import com.hotel.mapper.RoomTypeMapper;
import com.hotel.service.RoomTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/roomType")
public class RoomTypeController {

    private final RoomTypeService roomTypeService;
    private final RoomTypeMapper roomTypeMapper;

    public RoomTypeController(RoomTypeService roomTypeService, RoomTypeMapper mapper){
        this.roomTypeService = roomTypeService;
        this.roomTypeMapper = mapper;
    }

    // ---------------- CREATE ------------------
    @PostMapping
    public ResponseEntity<?> create(@RequestBody RoomTypeDTO dto){
        RoomType entity = roomTypeMapper.toEntity(dto);
        RoomType saved = roomTypeService.createRoomType(entity);
        RoomTypeDTO response = roomTypeMapper.toDto(saved);

        return ResponseEntity.ok(response);
    }

    // ---------------- UPDATE ------------------
    @PutMapping("/{id}")
    public ResponseEntity<RoomType> update(@PathVariable Long id,
                                    @RequestBody RoomTypeDTO dto){
        RoomType entity = roomTypeMapper.toEntity(dto);
        RoomType response = roomTypeService.updateRoomType(id, entity);
        return ResponseEntity.ok(response);
    }

    // ---------------- FIND BY ID ------------------
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        RoomType roomType = roomTypeService.getRoomTypeById(id);
        RoomTypeDTO dto = roomTypeMapper.toDto(roomType);

        return ResponseEntity.ok(dto);
    }

    // ---------------- DELETE ------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        roomTypeService.deleteRoomType(id);
        return ResponseEntity.ok("Room type deleted successfully");
    }
}
