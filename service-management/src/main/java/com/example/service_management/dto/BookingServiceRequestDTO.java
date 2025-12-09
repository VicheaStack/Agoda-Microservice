package com.example.service_management.dto;

import lombok.*;
import jakarta.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingServiceRequestDTO {

    @NotNull
    private Long bookingId;

    private String bookingReference;

    @NotNull
    private Long serviceId;

    private String serviceName;

    private Long staffId;
    private String staffName;

    @NotNull
    @Positive
    private Integer quantity;

    @NotNull
    @Positive
    private Double unitPrice;

    private String notes;
}
