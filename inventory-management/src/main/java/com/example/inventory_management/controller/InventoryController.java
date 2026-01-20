package com.example.inventory_management.controller;

import com.example.inventory_management.entity.Inventory;
import com.example.inventory_management.entity.dto.InventoryRequestDTO;
import com.example.inventory_management.entity.dto.InventoryResponseDTO;
import com.example.inventory_management.entity.dto.PageResponse;
import com.example.inventory_management.mapper.InventoryMapper;
import com.example.inventory_management.service.InventoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;
    private final InventoryMapper inventoryMapper;

    public InventoryController(InventoryService inventoryService,
                               InventoryMapper inventoryMapper) {
        this.inventoryService = inventoryService;
        this.inventoryMapper = inventoryMapper;
    }

    // success
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<?> create(@RequestBody InventoryRequestDTO requestDTO){
        Inventory entity = inventoryMapper.toEntity(requestDTO);
        Inventory inventory = inventoryService.addMoreStock(entity);
        InventoryResponseDTO responseDTO = inventoryMapper.toResponseDTO(inventory);
        return ResponseEntity.ok(responseDTO);
    }

    // success
    @PostMapping("/{id}")
    public ResponseEntity<?> updateMoreStock(@PathVariable Long id,
                                             @RequestBody InventoryRequestDTO requestDTO){
        Inventory entity = inventoryMapper.toEntity(requestDTO);
        Inventory inventory = inventoryService.updateStock(id, entity);
        InventoryResponseDTO responseDTO = inventoryMapper.toResponseDTO(inventory);
        return ResponseEntity.ok(responseDTO);
    }

    // success
    @GetMapping("/{id}")
    public ResponseEntity<?> findProductById(@PathVariable Long id){
        Inventory itemById = inventoryService.findItemById(id);
        InventoryResponseDTO responseDTO = inventoryMapper.toResponseDTO(itemById);
        return ResponseEntity.ok(responseDTO);
    }

    // clean up of mix with list and pagniation
    @GetMapping("/findProduct")
    public ResponseEntity<PageResponse<InventoryResponseDTO>> findAllProduct(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        // Fetch paginated inventory
        Page<Inventory> all = inventoryService.findAll(PageRequest.of(page, size));

        // Map Inventory -> DTO
        List<InventoryResponseDTO> result = all.getContent()
                .stream()
                .map(inventoryMapper::toResponseDTO)
                .collect(Collectors.toList());

        // Build page response using Page info
        PageResponse<InventoryResponseDTO> response = new PageResponse<>(
                result,               // content
                all.getNumber(),      // current page number
                all.getSize(),        // page size
                all.getTotalElements(), // total elements
                all.getTotalPages(),  // total pages
                all.isFirst(),        // is first page
                all.isLast()          // is last page
        );

        return ResponseEntity.ok(response);
    }

    //success
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        inventoryService.deleteStockById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/name")
    public ResponseEntity<PageResponse<InventoryResponseDTO>> findByItemNameIgnoreCase(
            @RequestParam String itemName,
            Pageable pageable
    ) {
        Page<InventoryResponseDTO> page = inventoryService
                .findByItemNameIgnoreCase(itemName, pageable)
                .map(inventoryMapper::toResponseDTO);

        PageResponse<InventoryResponseDTO> response = new PageResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isFirst(),
                page.isLast()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/search/category")
    public ResponseEntity<PageResponse<InventoryResponseDTO>> findByCategoryContainingIgnoreCase(
            @RequestParam String category,
            Pageable pageable
    ) {
        Page<InventoryResponseDTO> page = inventoryService
                .findByCategoryContainingIgnoreCase(category, pageable)
                .map(inventoryMapper::toResponseDTO);

        PageResponse<InventoryResponseDTO> response = new PageResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isFirst(),
                page.isLast()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/search/status")
    public ResponseEntity<PageResponse<InventoryResponseDTO>> findByStatusContainingIgnoreCase(
            @RequestParam String category,
            Pageable pageable
    ) {
        Page<InventoryResponseDTO> page = inventoryService
                .findByStatusContainingIgnoreCase(category, pageable)
                .map(inventoryMapper::toResponseDTO);

        PageResponse<InventoryResponseDTO> response = new PageResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isFirst(),
                page.isLast()
        );

        return ResponseEntity.ok(response);
    }


}
