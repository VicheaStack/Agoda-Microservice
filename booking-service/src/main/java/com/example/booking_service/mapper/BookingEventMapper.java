package com.example.booking_service.mapper;

import com.example.booking_service.dto.BookingEvent;
import com.example.booking_service.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
public interface BookingEventMapper {

    BookingEvent dto(Booking entity);

    Booking entity(BookingEvent dto);
}
