package com.example.booking_service.mapper;

import com.example.booking_service.dto.BookingCreateRequestDTO;
import com.example.booking_service.dto.BookingDto;
import com.example.booking_service.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookingMapper {

    BookingDto toDto(Booking entity);

    Booking toEntity(BookingCreateRequestDTO dto);
}
