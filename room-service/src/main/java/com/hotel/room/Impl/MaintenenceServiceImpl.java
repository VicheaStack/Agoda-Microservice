package com.hotel.room.Impl;

import com.hotel.room.config.ResourceNotFoundException;
import com.hotel.room.entity.Maintenance;
import com.hotel.room.repository.MaintenanceRepository;
import com.hotel.room.service.MaintenanceService;
import org.springframework.stereotype.Service;

@Service
public class MaintenenceServiceImpl implements MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;

    public MaintenenceServiceImpl (MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }

    @Override
    public Maintenance createMaintenance(Maintenance maintenance) {
        Maintenance save = maintenanceRepository.save(maintenance);
        return save;
    }

    @Override
    public Maintenance updateMaintenance(Long id, Maintenance maintenance) {
        Maintenance maintenan = maintenanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("can't find room maintenance"));

        maintenan.setIssueType(maintenance.getIssueType());
        maintenan.setDescription(maintenance.getDescription());
        maintenan.setPriority(maintenance.getPriority());

        Maintenance save = maintenanceRepository.save(maintenan);

        return save;
    }

    @Override
    public Maintenance getMaintenanceById(Long id) {
        Maintenance maintenance = maintenanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("don't need to maintenance"));
        return maintenance;
    }

    @Override
    public void deleteMaintenance(Long id) {
        if(!maintenanceRepository.existsById(id)){
            maintenanceRepository.deleteById(id);
        }
    }
}
