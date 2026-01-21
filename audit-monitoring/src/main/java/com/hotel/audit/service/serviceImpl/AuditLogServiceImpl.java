package com.hotel.audit.service.serviceImpl;

import com.hotel.audit.entity.AuditLog;
import com.hotel.audit.entity.dto.AuditLogRequestDTO;
import com.hotel.audit.entity.dto.AuditLogResponseDTO;
import com.hotel.audit.repository.AuditLogRepository;
import com.hotel.audit.service.AuditLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditLogServiceImpl implements AuditLogService {

    private final AuditLogRepository auditLogRepository;

    public AuditLogServiceImpl(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }


    @Override
    public AuditLog createAuditLog(AuditLog request) {
        AuditLog save = auditLogRepository.save(request);
        return save;
    }

    @Override
    public AuditLog getAuditLogById(Long id) {
        AuditLog auditLog = auditLogRepository
                .findById(id).orElseThrow(() -> new RuntimeException("Audit not found! "));
        return auditLog;
    }

    @Override
    public List<AuditLog> getAllAuditLogs() {
        List<AuditLog> all = auditLogRepository.findAll();
        return all;
    }
}
