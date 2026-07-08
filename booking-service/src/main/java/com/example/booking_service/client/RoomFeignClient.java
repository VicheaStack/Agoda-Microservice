package com.example.booking_service.client;

import com.example.booking_service.dto.RoomDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ROOM-SERVICE")
public interface RoomFeignClient {

    @PostMapping("/api/v1/rooms")
    RoomDTO create(@RequestBody RoomDTO roomDTO);

    @PutMapping("/api/v1/rooms/{id}")
    RoomDTO update(@RequestBody RoomDTO roomDTO,
                   @PathVariable("id") Long id);

    @GetMapping("/api/v1/rooms")
    Page<RoomDTO> checkAvailability(Pageable pageable);

    @GetMapping("/api/v1/rooms/{id}")
    RoomDTO getById(@PathVariable("id") Long id);

    @DeleteMapping("/api/v1/rooms/{id}")
    void delete(@PathVariable("id") Long id);

    @GetMapping("/api/v1/rooms/{roomId}/available")
    Boolean isRoomAvailable(@PathVariable("roomId") Long roomId);
}