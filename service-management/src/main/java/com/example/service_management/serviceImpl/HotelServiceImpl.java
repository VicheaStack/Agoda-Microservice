package com.example.service_management.serviceImpl;

import com.example.service_management.dto.ServiceRequestDTO;
import com.example.service_management.dto.ServiceResponseDTO;
import com.example.service_management.entity.ServiceManagement;
import com.example.service_management.repository.ServiceRepository;
import com.example.service_management.service.HotelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    private final ServiceRepository repository;

    public HotelServiceImpl(ServiceRepository repository){
        this.repository = repository;
    }

    @Override
    public List<ServiceManagement> getAllServices() {
        List<ServiceManagement> all = repository.findAll();

        if(all.isEmpty()){
            new RuntimeException("can't find any service");
        }

        return all;
    }

    @Override
    public ServiceManagement getServiceById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("can't find service"));
    }

    @Override
    public ServiceManagement createService(ServiceManagement request) {
        ServiceManagement save = repository.save(request);
        return save;
    }

    @Override
    public ServiceManagement updateService(Long id, ServiceManagement request) {
        ServiceManagement serviceManagement = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("can't find user "));

        serviceManagement.setName(request.getName());
        serviceManagement.setDescription(request.getDescription());
        serviceManagement.setPrice(request.getPrice());
        serviceManagement.setCategory(request.getCategory());
        serviceManagement.setStatus(request.getStatus());

        repository.save(serviceManagement);
        return serviceManagement;
    }

    @Override
    public void deleteService(Long id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
        }
    }
}
