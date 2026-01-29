package com.example.booking_service.entity.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingCreateEvent {
    private Long bookingId;
    private Long roomId;
    private String userEmail;

}
