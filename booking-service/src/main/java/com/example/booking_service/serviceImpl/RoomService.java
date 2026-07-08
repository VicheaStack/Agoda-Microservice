package com.example.booking_service.serviceImpl;

import com.example.booking_service.client.RoomFeignClient;
import com.example.booking_service.dto.RoomDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    private final RoomFeignClient roomFeignClient;

    public RoomService(RoomFeignClient roomFeignClient) {
        this.roomFeignClient = roomFeignClient;
    }

    public RoomDTO create(RoomDTO roomDTO) {
        return roomFeignClient.create(roomDTO);
    }

    public RoomDTO update(RoomDTO roomDTO, Long id) {
        return roomFeignClient.update(roomDTO, id);
    }

    public Page<RoomDTO> checkAvailability(Pageable pageable) {
        return roomFeignClient.checkAvailability(pageable);
    }

    public RoomDTO getById(Long id) {
        return roomFeignClient.getById(id);
    }

    public void delete(Long id) {
        roomFeignClient.delete(id);
    }

    public Boolean isRoomAvailable(Long roomId) {
        return roomFeignClient.isRoomAvailable(roomId);
    }
}