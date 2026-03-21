package com.example.booking_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RoomAvailabilityPage {
    private List<RoomBookingSnapshotDTO> content;

    public List<RoomBookingSnapshotDTO> getContent() {
        return content;
    }

    public void setContent(List<RoomBookingSnapshotDTO> content) {
        this.content = content;
    }
}