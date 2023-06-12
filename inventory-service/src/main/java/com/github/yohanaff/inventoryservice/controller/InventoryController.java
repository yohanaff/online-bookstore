package com.github.yohanaff.inventoryservice.controller;// import statements here

import com.github.yohanaff.inventoryservice.entity.Inventory;
import com.github.yohanaff.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<List<Inventory>> getAllInventory() {
        return ResponseEntity.ok(inventoryService.getAllInventory());
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Inventory> getInventoryByBookId(@PathVariable Long bookId) {
        Optional<Inventory> inventory = inventoryService.getInventoryByBookId(bookId);
        if (inventory.isPresent()) {
            return ResponseEntity.ok(inventory.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Inventory> saveInventory(@RequestBody Inventory inventory) {
        return ResponseEntity.ok(inventoryService.saveInventory(inventory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteInventory(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
        return ResponseEntity.noContent().build();
    }
}
