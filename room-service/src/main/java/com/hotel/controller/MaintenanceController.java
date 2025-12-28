package com.hotel.controller;

import com.hotel.dto.MaintenanceDTO;
import com.hotel.entity.Maintenance;
import com.hotel.mapper.MaintenanceMapper;
import com.hotel.service.MaintenanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/maintenance")
public class MaintenanceController {

    private final MaintenanceService maintenanceService;
    private final MaintenanceMapper maintenanceMapper;

    public MaintenanceController(MaintenanceService maintenanceService,
                                 MaintenanceMapper maintenanceMapper) {
        this.maintenanceService = maintenanceService;
        this.maintenanceMapper = maintenanceMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MaintenanceDTO> create(@RequestBody MaintenanceDTO dto) {
        Maintenance entity = maintenanceMapper.toEntity(dto);
        Maintenance maintenance = maintenanceService.createMaintenance(entity);
        MaintenanceDTO response = maintenanceMapper.toDto(maintenance);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaintenanceDTO> update(@PathVariable Long id,
                                                 @RequestBody MaintenanceDTO dto) {
        Maintenance entity = maintenanceMapper.toEntity(dto);
        Maintenance maintenance = maintenanceService.updateMaintenance(id, entity);
        MaintenanceDTO response = maintenanceMapper.toDto(maintenance);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceDTO> findById(@PathVariable Long id) {
        Maintenance maintenanceById = maintenanceService.getMaintenanceById(id);
        MaintenanceDTO dto = maintenanceMapper.toDto(maintenanceById);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        maintenanceService.deleteMaintenance(id);
        return ResponseEntity.noContent().build();
    }

    // Optional: Add getAll endpoint if needed
//    @GetMapping
//    public ResponseEntity<List<MaintenanceDTO>> getAll() {
//        List<Maintenance> allMaintenance = maintenanceService.getAllMaintenance();
//        List<MaintenanceDTO> dtos = allMaintenance.stream()
//                .map(maintenanceMapper::toDto)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(dtos);
//    }
//
//    // Optional: Add findByStatus endpoint if needed
//    @GetMapping("/status/{status}")
//    public ResponseEntity<List<MaintenanceDTO>> findByStatus(@PathVariable String status) {
//        List<Maintenance> maintenanceList = maintenanceService.getMaintenanceByStatus(status);
//        List<MaintenanceDTO> dtos = maintenanceList.stream()
//                .map(maintenanceMapper::toDto)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(dtos);
//    }
}