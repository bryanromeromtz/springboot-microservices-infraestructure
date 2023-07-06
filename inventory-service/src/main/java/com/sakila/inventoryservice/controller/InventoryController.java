package com.sakila.inventoryservice.controller;

import com.sakila.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
@Slf4j
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/{sku-code}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> isInStock(@PathVariable("sku-code") String skuCode) {
        boolean isAvailable = inventoryService.isInStock(skuCode);

        if (isAvailable) {
            return ResponseEntity.ok("Item is in stock");
        } else {
            return ResponseEntity.ok("Item is out of stock");
        }
    }

}