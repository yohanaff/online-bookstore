package com.github.yohanaff.inventoryservice.kafka;

import com.github.yohanaff.inventoryservice.kafka.model.InventoryEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class InventoryEventProducer {

    private final KafkaTemplate<String, InventoryEvent> kafkaTemplate;

    public InventoryEventProducer(KafkaTemplate<String, InventoryEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendInventoryEvent(InventoryEvent inventoryEvent) {
        this.kafkaTemplate.send("inventory", inventoryEvent);
    }
}
