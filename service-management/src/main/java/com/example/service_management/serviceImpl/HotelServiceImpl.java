package com.example.service_management.serviceImpl;

import com.example.service_management.config.ResourceNotFoundException;
import com.example.service_management.entity.ServiceManagement;
import com.example.service_management.repository.ServiceRepository;
import com.example.service_management.service.HotelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class HotelServiceImpl implements HotelService {

    private final ServiceRepository repository;

    public HotelServiceImpl(ServiceRepository repository){
        this.repository = repository;
    }

    @Override
    public List<ServiceManagement> getAllServices() {
        log.info("Fetching all services...");
        List<ServiceManagement> all = repository.findAll();

        if (all.isEmpty()) {
            log.warn("No services found in the database");
            throw new ResourceNotFoundException("Can't find any service");
        }

        log.info("Successfully retrieved {} services", all.size());
        return all;
    }

    @Override
    public ServiceManagement getServiceById(Long id) {
        log.info("Fetching service with ID: {}", id);

        return repository.findById(id)
                .map(service -> {
                    log.info("Service found: {}", service.getName());
                    return service;
                })
                .orElseThrow(() -> {
                    log.error("Service with ID {} not found", id);
                    return new ResourceNotFoundException("Can't find service");
                });
    }

    @Override
    public ServiceManagement createService(ServiceManagement request) {
        log.info("Creating new service: {}", request.getName());
        ServiceManagement save = repository.save(request);
        log.info("Service created successfully with ID: {}", save.getId());
        return save;
    }

    @Override
    public ServiceManagement updateService(Long id, ServiceManagement request) {
        log.info("Updating service with ID: {}", id);

        ServiceManagement serviceManagement = repository.findById(id)
                .orElseThrow(() -> {
                    log.error("Cannot update - service with ID {} not found", id);
                    return new ResourceNotFoundException("Can't find service");
                });

        serviceManagement.setName(request.getName());
        serviceManagement.setDescription(request.getDescription());
        serviceManagement.setPrice(request.getPrice());
        serviceManagement.setCategory(request.getCategory());
        serviceManagement.setStatus(request.getStatus());

        repository.save(serviceManagement);

        log.info("Service with ID {} updated successfully", id);
        return serviceManagement;
    }

    @Override
    public void deleteService(Long id) {
        log.info("Attempting to delete service with ID: {}", id);

        if (repository.existsById(id)) {
            repository.deleteById(id);
            log.info("Service with ID {} deleted successfully", id);
        } else {
            log.warn("Delete failed - service with ID {} does not exist", id);
        }
    }
}
