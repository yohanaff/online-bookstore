package com.github.yohanaff.catalog.kafka;

import com.github.yohanaff.catalog.kafka.model.InventoryEvent;
import com.github.yohanaff.catalog.service.InventoryCache;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class InventoryEventConsumer {

    private final InventoryCache inventoryCache;

    public InventoryEventConsumer(InventoryCache inventoryCache) {
        this.inventoryCache = inventoryCache;
    }

    @KafkaListener(topics = "inventory", groupId = "bookstore")
    public void listen(InventoryEvent inventoryEvent) {
        inventoryCache.update(inventoryEvent.getBookId(), inventoryEvent.getQuantity());
    }
}
