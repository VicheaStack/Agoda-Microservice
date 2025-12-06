package com.group.learn.controller;

import com.group.learn.dto.GuestDTO;
import com.group.learn.dto.GuestResponseDTO;
import com.group.learn.entity.Guest;
import com.group.learn.mapper.GuestMapper;
import com.group.learn.service.GuestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/guests")
public class GuestController {

    private final GuestService guestService;
    private final GuestMapper guestMapper; // injected

    public GuestController(GuestService guestService, GuestMapper guestMapper) {
        this.guestService = guestService;
        this.guestMapper = guestMapper;
    }

    @PostMapping
    public ResponseEntity<GuestResponseDTO> createGuest(@Validated @RequestBody GuestDTO dto) {
        log.info("Creating guest {}", dto);
        Guest guestEntity = guestMapper.toEntity(dto);
        Guest createdGuest = guestService.create(guestEntity);
        GuestResponseDTO response = guestMapper.toResponseDto(createdGuest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GuestResponseDTO> updateGuest(@Validated @PathVariable Long id,
                                                @RequestBody GuestDTO dto) {
        log.info("Updating guest with id {}", id);
        Guest guestEntity = guestMapper.toEntity(dto);
        Guest updatedGuest = guestService.update(id, guestEntity);
        GuestResponseDTO response = guestMapper.toResponseDto(updatedGuest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuest(@PathVariable Long id) {
        log.info("Deleting guest {}", id);
        guestService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
