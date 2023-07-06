package com.sakila.inventoryservice.service;

import com.sakila.inventoryservice.model.Inventory;
import com.sakila.inventoryservice.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public boolean isInStock(String skuCode) {
        Optional<Inventory> inventory = inventoryRepository.findBySkuCode(skuCode);
        return inventory.isPresent() && inventory.get().getQuantity() > 0;
    }

}
