package com.github.yohanaff.inventoryservice.repository;

import com.github.yohanaff.inventoryservice.service.BookInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookInventoryRepository extends JpaRepository<BookInventory, Long> {
    Optional<BookInventory> findByBookId(Long bookId);
}