package com.hotel.audit.controller;

import com.hotel.audit.entity.dto.AuditLogRequestDTO;
import com.hotel.audit.entity.dto.AuditLogResponseDTO;
import com.hotel.audit.mapper.AuditLogMapper;
import com.hotel.audit.service.AuditLogService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audit-logs")
public class AuditLogController {

    private final AuditLogService auditLogService;
    private final AuditLogMapper auditLogMapper;

    public AuditLogController(AuditLogService auditLogService,
                              AuditLogMapper auditLogMapper) {
        this.auditLogService = auditLogService;
        this.auditLogMapper = auditLogMapper;
    }

    @PostMapping
    public ResponseEntity<AuditLogResponseDTO> createAuditLog(@Valid @RequestBody AuditLogRequestDTO requestDTO) {
        var auditLog = auditLogMapper.toEntity(requestDTO);
        var savedAuditLog = auditLogService.createAuditLog(auditLog);
        var responseDTO = auditLogMapper.toResponseDTO(savedAuditLog);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuditLogResponseDTO> getAuditLogById(@PathVariable Long id) {
        var auditLog = auditLogService.getAuditLogById(id);
        var responseDTO = auditLogMapper.toResponseDTO(auditLog);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<AuditLogResponseDTO>> getAllAuditLogs() {
        var auditLogs = auditLogService.getAllAuditLogs();
        var responseDTOs = auditLogs.stream()
                .map(auditLogMapper::toResponseDTO)
                .toList();

        return ResponseEntity.ok(responseDTOs);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuditLog(@PathVariable Long id) {
        auditLogService.getAuditLogById(id); // Check if exists
        return ResponseEntity.noContent().build();
    }
}