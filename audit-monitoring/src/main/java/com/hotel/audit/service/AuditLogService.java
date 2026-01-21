package com.hotel.audit.service;

import com.hotel.audit.entity.AuditLog;
import com.hotel.audit.entity.dto.AuditLogRequestDTO;
import com.hotel.audit.entity.dto.AuditLogResponseDTO;

import java.util.List;

public interface AuditLogService {

    AuditLog createAuditLog(AuditLog request);

    AuditLog getAuditLogById(Long id);

    List<AuditLog> getAllAuditLogs();
}
