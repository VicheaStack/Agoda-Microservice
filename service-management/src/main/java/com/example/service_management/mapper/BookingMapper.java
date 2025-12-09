package com.example.service_management.mapper;

import com.example.service_management.dto.BookingServiceRequestDTO;
import com.example.service_management.dto.BookingServiceResponseDTO;
import com.example.service_management.entity.BookingService;
import com.example.service_management.entity.ServiceManagement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "Spring")
public interface BookingMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    BookingService entity(BookingServiceResponseDTO dto);

    BookingServiceRequestDTO toDtoRequest(BookingService entity);
    BookingServiceResponseDTO toDto(BookingService entity);
}
