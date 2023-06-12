package com.github.yohanaff.inventoryservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    private Long id;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    public Inventory() {
    }

    public Inventory(Long id, Long bookId, Integer quantity, LocalDateTime lastUpdate) {
        this.id = id;
        this.bookId = bookId;
        this.quantity = quantity;
        this.lastUpdate = lastUpdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}