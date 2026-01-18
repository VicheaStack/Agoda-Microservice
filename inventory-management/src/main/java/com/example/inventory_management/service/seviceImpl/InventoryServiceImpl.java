package com.example.inventory_management.service.seviceImpl;

import com.example.inventory_management.entity.Inventory;
import com.example.inventory_management.repository.InventoryRepository;
import com.example.inventory_management.service.InventoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public Inventory addMoreStock(Inventory inventory) {
        Inventory save = inventoryRepository.save(inventory);
        return save;
    }

    @Override
    public Inventory updateStock(Long id, Inventory inventory) {
        Inventory stock = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found Item in stock"));

        stock.setItemName(inventory.getItemName());
        stock.setQuantity(inventory.getQuantity());
        stock.setReorderLevel(inventory.getReorderLevel());
        stock.setUnitPrice(inventory.getUnitPrice());
        stock.setStatus(inventory.getStatus());

        return stock;
    }

    @Override
    public Inventory findItemById(Long id) {
        Inventory findById = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("can't find Item"));
        return findById;
    }

    @Override
    public Page<Inventory> findAll(Pageable pageable) {
        Page<Inventory> all = inventoryRepository.findAll(pageable);

        if (all.getContent().isEmpty()) {
            throw new NoSuchElementException("No inventory found for the requested page");
        }
        return all;
    }

    @Override
    public void deleteStockById(Long id) {
        if (inventoryRepository.existsById(id)) {
            throw new RuntimeException("item not found by id");
        } else {
            inventoryRepository.deleteById(id);
        }
    }

    public Page<Inventory> findByItemNameIgnoreCase(String itemName ,Pageable pageable){
        return inventoryRepository
                .findByItemNameIgnoreCase(itemName, pageable);
    }

}
