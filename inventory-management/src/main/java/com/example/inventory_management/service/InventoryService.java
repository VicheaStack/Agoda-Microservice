package com.example.inventory_management.service;

import com.example.inventory_management.entity.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface InventoryService {

    Inventory addMoreStock(Inventory inventory);
    Inventory updateStock(Long id, Inventory inventory);
    Inventory findItemById(Long id);

    Page<Inventory> findAll(Pageable pageable);
    void deleteStockById(Long id);

    // Business logic
    Page<Inventory> findByItemNameIgnoreCase(String itemName, Pageable pageable);
    Page<Inventory> findByStatusContainingIgnoreCase(String status, Pageable pageable);
    Page<Inventory> findByCategoryContainingIgnoreCase(String category, Pageable pageable);

}
