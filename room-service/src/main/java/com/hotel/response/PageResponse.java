package com.hotel.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class PageResponse<T> {
    private List<T> content;
    private int currentPage;
    private int totalPage;
    private int totalElement;
    private int size;
}
