package com.example.service_management.service;

import com.example.service_management.dto.ServiceRequestDTO;
import com.example.service_management.dto.ServiceResponseDTO;
import com.example.service_management.entity.ServiceManagement;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HotelService {

    List<ServiceManagement> getAllServices();

    ServiceManagement getServiceById(Long id);

    ServiceManagement createService(ServiceManagement request);

    ServiceManagement updateService(Long id, ServiceManagement request);

    void deleteService(Long id);
}
