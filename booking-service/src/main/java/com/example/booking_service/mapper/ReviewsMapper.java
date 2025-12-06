package com.example.booking_service.mapper;

import com.example.booking_service.dto.ReviewsDto;
import com.example.booking_service.entity.Reviews;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewsMapper {

    ReviewsDto toDto(Reviews entity);

    Reviews toEntity(ReviewsDto dto);
}
