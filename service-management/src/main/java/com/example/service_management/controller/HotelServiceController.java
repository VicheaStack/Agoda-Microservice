package com.example.service_management.controller;

import com.example.service_management.dto.ServiceRequestDTO;
import com.example.service_management.dto.ServiceResponseDTO;
import com.example.service_management.entity.ServiceManagement;
import com.example.service_management.mapper.ServiceMapper;
import com.example.service_management.service.HotelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/serviceManagement")
public class HotelServiceController {

    private final HotelService hotelService;
    private final ServiceMapper serviceMapper;

    public HotelServiceController(HotelService hotelService,
                                  ServiceMapper serviceMapper) {
        this.hotelService = hotelService;
        this.serviceMapper = serviceMapper;
    }

    @PostMapping
    public ResponseEntity<ServiceResponseDTO> create(@RequestBody ServiceRequestDTO dto) {
        ServiceManagement entity = serviceMapper.toEntity(dto);
        ServiceManagement service = hotelService.createService(entity);
        ServiceResponseDTO responseDto = serviceMapper.toResponseDto(service);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceResponseDTO> update(@PathVariable Long id,
                                                     @RequestBody ServiceRequestDTO dto) {
        ServiceManagement entity = serviceMapper.toEntity(dto);
        ServiceManagement updated = hotelService.updateService(id, entity);
        ServiceResponseDTO responseDto = serviceMapper.toResponseDto(updated);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponseDTO> getById(@PathVariable Long id) {
        ServiceManagement serviceById = hotelService.getServiceById(id);
        ServiceResponseDTO responseDto = serviceMapper.toResponseDto(serviceById);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/search")
    public ResponseEntity<List<ServiceResponseDTO>> findAll(@RequestBody(required = false) ServiceRequestDTO dto) {
        List<ServiceManagement> allServices = hotelService.getAllServices(); // implement filtering if needed
        List<ServiceResponseDTO> collect = allServices.stream()
                .map(serviceMapper::toResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(collect);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        hotelService.deleteService(id);
        return ResponseEntity.noContent().build();
    }
}
