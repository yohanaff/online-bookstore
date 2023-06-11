package com.github.yohanaff.catalog.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookTest {

    @Test
    void testGettersAndSetters() {
        Book book = new Book();

        book.setTitle("Test Book");
        book.setAuthor("John Doe");
        book.setIsbn("1234567890");
        book.setPrice(BigDecimal.valueOf(9.99));
        book.setPublicationYear(2021);
        book.setCategory(Category.FICTION);

        assertEquals("Test Book", book.getTitle());
        assertEquals("John Doe", book.getAuthor());
        assertEquals("1234567890", book.getIsbn());
        assertEquals(BigDecimal.valueOf(9.99), book.getPrice());
        assertEquals(2021, book.getPublicationYear());
        assertEquals(Category.FICTION, book.getCategory());
    }
}