package com.github.yohanaff.catalog.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class InventoryCache {

    private final Map<Long, Integer> cache = new ConcurrentHashMap<>();

    public void update(Long bookId, Integer quantity) {
        cache.put(bookId, quantity);
    }
}