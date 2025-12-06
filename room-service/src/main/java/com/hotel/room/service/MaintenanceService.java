package com.hotel.room.service;

import com.hotel.room.entity.Maintenance;
import org.springframework.stereotype.Service;

@Service
public interface MaintenanceService {

    Maintenance createMaintenance(Maintenance maintenance);
    Maintenance updateMaintenance(Long id, Maintenance maintenance);
    Maintenance getMaintenanceById(Long id);
    void deleteMaintenance(Long id);
}
