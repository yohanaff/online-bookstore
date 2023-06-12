package com.github.yohanaff.inventoryservice.repository;

import com.github.yohanaff.inventoryservice.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByBookId(Long bookId);
}