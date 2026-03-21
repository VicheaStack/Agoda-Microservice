package com.example.booking_service.mapper;

import com.example.booking_service.dto.BookingCreateRequestDTO;
import com.example.booking_service.dto.BookingDto;
import com.example.booking_service.entity.Booking;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface BookingMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bookingReference", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "finalAmount", ignore = true)
    @Mapping(target = "actualCheckIn", ignore = true)
    @Mapping(target = "actualCheckOut", ignore = true)
    @Mapping(target = "cancellationReason", ignore = true)
    @Mapping(source = "checkInDate", target = "checkInDate")
    @Mapping(source = "checkOutDate", target = "checkOutDate")
    @Mapping(source = "status", target = "status")
    Booking toEntity(BookingCreateRequestDTO dto);

    @InheritConfiguration(name = "toEntity")
    void updateEntityFromDto(BookingCreateRequestDTO dto, @MappingTarget Booking entity);

    BookingDto toDto(Booking entity);
}