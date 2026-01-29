package com.hotel.entity.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingCreatedEvent {
    private Long bookingId;
    private Long roomId;
    private String userEmail;
}
