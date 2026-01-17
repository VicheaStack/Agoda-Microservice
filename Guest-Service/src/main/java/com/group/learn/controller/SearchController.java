package com.group.learn.controller;

import com.group.learn.dto.GuestDTO;
import com.group.learn.dto.GuestResponseDTO;
import com.group.learn.entity.Guest;
import com.group.learn.mapper.GuestMapper;
import com.group.learn.service.GuestQueryMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/search")
public class SearchController {

    private final GuestQueryMethod queryMethod;
    private final GuestMapper guestMapper;

    public SearchController(GuestQueryMethod queryMethod,
                            GuestMapper guestMapper
    ) {
        this.queryMethod = queryMethod;
        this.guestMapper = guestMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuestDTO> findGuestById(@PathVariable Long id) {
        log.info("Fetching guest by ID: {}", id);
        Guest guestById = queryMethod.getGuestById(id);

        if (guestById == null) {
            log.warn("Guest not found with ID: {}", id);
            return ResponseEntity.notFound().build();
        }

        GuestDTO dto = guestMapper.toDTO(guestById);
        log.info("Guest found successfully: {}", dto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<GuestResponseDTO>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        log.info("Fetching all guests - page: {}, size: {}", page, size);
        Page<Guest> allGuests = queryMethod.getAllGuests(PageRequest.of(page, size));

        List<GuestResponseDTO> dtos = allGuests.getContent()
                .stream()
                .map(guestMapper::toResponseDto)
                        .collect(Collectors.toList());

        log.info("Returning {} guests", dtos.size());
        return ResponseEntity.ok(dtos);
    }

}
