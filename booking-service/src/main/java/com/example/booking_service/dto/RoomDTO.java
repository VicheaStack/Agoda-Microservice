package com.example.booking_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Setter
@Getter
// This needs roomTypeId!
public class RoomDTO {
    private String roomNumber;
    private Long roomTypeId;
    private String roomTypeName;
    private Integer floor;
    private String status;
    private BigDecimal pricePerNight;
    private Boolean breakfastIncluded;
    private Boolean smokingAllowed;
    private String description;
    // ...
}