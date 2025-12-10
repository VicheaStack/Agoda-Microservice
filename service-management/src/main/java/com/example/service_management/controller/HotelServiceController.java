package com.example.service_management.controller;

import com.example.service_management.dto.ServiceRequestDTO;
import com.example.service_management.dto.ServiceResponseDTO;
import com.example.service_management.entity.ServiceManagement;
import com.example.service_management.mapper.ServiceMapper;
import com.example.service_management.service.HotelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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
        log.info("API CALL: Create service - Request Name: {}", dto.getName());

        ServiceManagement entity = serviceMapper.toEntity(dto);
        ServiceManagement saved = hotelService.createService(entity);
        ServiceResponseDTO responseDto = serviceMapper.toResponseDto(saved);

        log.info("Service created successfully with ID: {}", saved.getId());
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceResponseDTO> update(@PathVariable Long id,
                                                     @RequestBody ServiceRequestDTO dto) {
        log.info("API CALL: Update service - ID: {}", id);

        ServiceManagement entity = serviceMapper.toEntity(dto);
        ServiceManagement updated = hotelService.updateService(id, entity);
        ServiceResponseDTO responseDto = serviceMapper.toResponseDto(updated);

        log.info("Service updated successfully - ID: {}", id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponseDTO> getById(@PathVariable Long id) {
        log.info("API CALL: Get service by ID: {}", id);

        ServiceManagement service = hotelService.getServiceById(id);
        ServiceResponseDTO responseDto = serviceMapper.toResponseDto(service);

        log.info("Service found - ID: {}", id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ServiceResponseDTO>> findAll(@RequestBody(required = false) ServiceRequestDTO dto) {
        log.info("API CALL: Search services (currently returning all)");

        List<ServiceManagement> allServices = hotelService.getAllServices();
        List<ServiceResponseDTO> list = allServices.stream()
                .map(serviceMapper::toResponseDto)
                .collect(Collectors.toList());

        log.info("Total services returned: {}", list.size());
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("API CALL: Delete service - ID: {}", id);

        hotelService.deleteService(id);

        log.info("Service deleted successfully - ID: {}", id);
        return ResponseEntity.noContent().build();
    }
}
