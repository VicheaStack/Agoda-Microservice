package com.example.inventory_management.mapper;

import com.example.inventory_management.entity.Inventory;
import com.example.inventory_management.entity.dto.InventoryRequestDTO;
import com.example.inventory_management.entity.dto.InventoryResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapper {

    public Inventory toEntity(InventoryRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Inventory inventory = new Inventory();
        inventory.setItemName(dto.itemName());
        inventory.setCategory(dto.category());
        inventory.setQuantity(dto.quantity());
        inventory.setReorderLevel(dto.reorderLevel());
        inventory.setUnitPrice(dto.unitPrice());
        inventory.setStatus(dto.status());

        return inventory;
    }

    public InventoryRequestDTO toRequestDTO(Inventory entity) {
        if (entity == null) {
            return null;
        }

        return new InventoryRequestDTO(
                entity.getItemName(),
                entity.getCategory(),
                entity.getQuantity(),
                entity.getReorderLevel(),
                entity.getUnitPrice(),
                entity.getStatus()
        );
    }

    public InventoryResponseDTO toResponseDTO(Inventory entity) {
        if (entity == null) {
            return null;
        }

        return new InventoryResponseDTO(
                entity.getId(),
                entity.getItemName(),
                entity.getCategory(),
                entity.getQuantity(),
                entity.getReorderLevel(),
                entity.getUnitPrice(),
                entity.getStatus(),
                entity.getLastUpdated()
        );
    }
}
