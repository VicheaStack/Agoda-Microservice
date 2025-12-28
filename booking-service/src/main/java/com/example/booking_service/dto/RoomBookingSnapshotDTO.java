package com.example.booking_service.dto;


import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomBookingSnapshotDTO {

    @NotNull
    private Long roomId;

    @NotNull
    private Long roomTypeId;

    @NotNull
    private String roomNumber;

    @NotNull
    private Boolean available;

    // 🔴 price at booking time (snapshot, NOT dynamic)
    @NotNull
    private BigDecimal pricePerNight;

    private Boolean breakfastIncluded;

    private Boolean smokingAllowed;
}
