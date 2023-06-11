package com.github.yohanaff.catalog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.yohanaff.catalog.model.Book;
import com.github.yohanaff.catalog.model.Category;
import com.github.yohanaff.catalog.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookService bookService;

    @Test
    void testGetAllBooks() throws Exception {
        Book book = new Book();

        book.setTitle("Test Book");
        book.setAuthor("John Doe");
        book.setIsbn("1234567890");
        book.setPrice(BigDecimal.valueOf(9.99));
        book.setPublicationYear(2021);
        book.setCategory(Category.FICTION);

        Pageable pageable = PageRequest.of(0, 10, Sort.by("id"));
        Page<Book> bookPage = new PageImpl<>(List.of(book), pageable, 1);
        when(bookService.findAllBooks(pageable)).thenReturn(bookPage.toList());

        mockMvc.perform(get("/api/books?page=0&size=10&sort=id"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title").value("Test Book"))
                .andExpect(jsonPath("$[0].author").value("John Doe"));

        verify(bookService, times(1)).findAllBooks(pageable);
    }

    @Test
    void testGetBookById() throws Exception {
        Book book = new Book();

        book.setTitle("Test Book");
        book.setAuthor("John Doe");
        book.setIsbn("1234567890");
        book.setPrice(BigDecimal.valueOf(9.99));
        book.setPublicationYear(2021);
        book.setCategory(Category.FICTION);

        when(bookService.findBookById(1L)).thenReturn(Optional.of(book));

        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Test Book"))
                .andExpect(jsonPath("$.author").value("John Doe"));

        verify(bookService, times(1)).findBookById(1L);
    }

    @Test
    void testGetBookByIdNotFound() throws Exception {
        when(bookService.findBookById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isNotFound());

        verify(bookService, times(1)).findBookById(1L);
    }

    @Test
    void testCreateBook() throws Exception {
        Book book = new Book();

        book.setTitle("Test Book");
        book.setAuthor("John Doe");
        book.setIsbn("1234567890");
        book.setPrice(BigDecimal.valueOf(9.99));
        book.setPublicationYear(2021);
        book.setCategory(Category.FICTION);

        when(bookService.saveBook(any(Book.class))).thenReturn(book);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Test Book"))
                .andExpect(jsonPath("$.author").value("John Doe"));

        verify(bookService, times(1)).saveBook(any(Book.class));
    }

    @Test
    void testUpdateBook() throws Exception {
        Book book = new Book();

        book.setTitle("Test Book");
        book.setAuthor("John Doe");
        book.setIsbn("1234567890");
        book.setPrice(BigDecimal.valueOf(9.99));
        book.setPublicationYear(2021);
        book.setCategory(Category.FICTION);

        when(bookService.saveBook(any(Book.class))).thenReturn(book);

        mockMvc.perform(put("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Test Book"))
                .andExpect(jsonPath("$.author").value("John Doe"));

        verify(bookService, times(1)).saveBook(any(Book.class));
    }

    @Test
    void testDeleteBook() throws Exception {
        mockMvc.perform(delete("/api/books/1"))
                .andExpect(status().isNoContent());

        verify(bookService, times(1)).deleteBook(1L);
    }
}
