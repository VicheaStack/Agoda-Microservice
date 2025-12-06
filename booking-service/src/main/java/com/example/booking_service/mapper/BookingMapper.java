package com.example.booking_service.mapper;

import com.example.booking_service.dto.BookingDto;
import com.example.booking_service.entity.Booking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    BookingDto toDto(Booking entity);

    Booking toEntity(BookingDto dto);
}
