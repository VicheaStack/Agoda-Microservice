package com.group.learn.mapper;

import org.mapstruct.Named;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DateMapper {

    @Named("timestampToString")
    public String timestampToString(LocalDateTime date) {
        return date != null ? date.toString() : null;
    }

    @Named("stringToTimestamp")
    public LocalDateTime stringToTimestamp(String value) {
        return value != null ? LocalDateTime.parse(value) : null;
    }
}
