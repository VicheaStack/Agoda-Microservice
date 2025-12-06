package com.example.booking_service.mapper;

import com.example.booking_service.dto.PromotionsDto;
import com.example.booking_service.entity.Promotions;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PromotionsMapper {

    PromotionsDto toDto(Promotions entity);

    Promotions toEntity(PromotionsDto dto);
}
