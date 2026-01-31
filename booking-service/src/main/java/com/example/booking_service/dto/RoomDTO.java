package com.example.booking_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@NoArgsConstructor  // CRITICAL: Add this for Jackson
@AllArgsConstructor
public class RoomDTO {
    private String roomNumber;
    private Long roomTypeId;
    private String roomTypeName;
    private Integer floor;
    private String status;  // Change from RoomStatus enum to String
    private BigDecimal pricePerNight;
    private Boolean breakfastIncluded;
    private Boolean smokingAllowed;
    private String description;

}