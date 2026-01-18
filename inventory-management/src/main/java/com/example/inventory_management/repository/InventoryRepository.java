package com.example.inventory_management.repository;

import com.example.inventory_management.entity.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long > {

    Page<Inventory> findByItemNameIgnoreCase(String itemName, Pageable pageable);
    Page<Inventory> findByStatusContainingIgnoreCase(String status, Pageable pageable);
    Page<Inventory> findByCategoryContainingIgnoreCase(String category, Pageable pageable);
}
