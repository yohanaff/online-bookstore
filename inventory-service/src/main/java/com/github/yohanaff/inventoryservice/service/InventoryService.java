package com.github.yohanaff.inventoryservice.service;// import statements here

import com.github.yohanaff.inventoryservice.entity.Inventory;
import com.github.yohanaff.inventoryservice.kafka.InventoryEventProducer;
import com.github.yohanaff.inventoryservice.kafka.model.InventoryEvent;
import com.github.yohanaff.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    private final InventoryEventProducer eventProducer;

    public InventoryService(InventoryEventProducer eventProducer) {
        this.eventProducer = eventProducer;
    }

    public void updateInventory(Long bookId, Integer quantity) {
        InventoryEvent event = new InventoryEvent(bookId, quantity);
        eventProducer.sendInventoryEvent(event);
    }

    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    public Optional<Inventory> getInventoryByBookId(Long bookId) {
        return inventoryRepository.findByBookId(bookId);
    }

    public Inventory saveInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }
}
