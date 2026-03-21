package com.example.booking_service.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomBookingSnapshotDTO {

    @JsonProperty("id")
    @NotNull
    private Long roomId;

    @NotNull
    private Long roomTypeId;

    @NotNull
    private String roomNumber;

    @NotNull
    private Boolean available;

    @NotNull
    private BigDecimal pricePerNight;

    private Boolean breakfastIncluded;

    private Boolean smokingAllowed;
}
