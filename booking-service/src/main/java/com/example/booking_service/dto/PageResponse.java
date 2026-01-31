package com.example.booking_service.dto;

import com.example.booking_service.entity.Booking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> {
    private List<T> content;
    private int currentPage;
    private int totalPage;      // Note: this is totalPage (singular)
    private long totalElement;  // Note: this is totalElement (singular)
    private int size;
}
